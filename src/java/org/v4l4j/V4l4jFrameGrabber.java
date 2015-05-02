/*
 * The compilation of software known as V4L4J is distributed under the
 * following terms:
 *
 * Copyright (c) 2015 Christopher Friedt. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS ``AS IS'' AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED.  IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 */

package org.v4l4j;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.*;

import javax.imageio.*;

public class V4l4jFrameGrabber {
	
	static final int VIDEO_MAX_FRAME;
	static final int VIDEO_MAX_PLANES;

	static {
		VIDEO_MAX_FRAME = 32;
		VIDEO_MAX_PLANES = 8;
	}
	
	private static boolean debug;
	private static boolean verbose;
	
	static final Set<PixelFormat> supported_pixfmts;
	static final HashSet<Capability> at_least_one;
	
	String fn = "/dev/video0";
	String ofn = "/tmp/out.png";
	int width = 640;
	int height = 480;
	int nbuf = 5;

	RandomAccessFile raf;
	int fdint;
	FileDescriptor fd;
	FileChannel fc;

	V4L4JCapability cap;
	Set<Capability> cap_set;
	ArrayList<PixelFormat> available_pixfmts;
	PixelFormat pixel_format;

	ByteBuffer[] buffer;
	
	V4L4JRequestBuffers reqbuf;
	
	IntPointer type;
	
	byte[] rgb24;
	byte[] png;
	
	public V4l4jFrameGrabber() {
		type = new IntPointer.ByReference();
		type.value = BufferType.V4L2_BUF_TYPE_VIDEO_CAPTURE.toInteger();
	}

	void prepare() throws Throwable {
		raf = new RandomAccessFile( fn, "rw" );
		fc = raf.getChannel();
		fd = raf.getFD();
		check_capabilities();
		choose_pixfmt();
		set_width_height_framerate();
		map_buffers();
	}

	void streamOn() throws IOException {
		Ioctl.VIDIOC_STREAMON.ioctl(fd, type );
	}
	
	void streamOff() throws IOException {
		Ioctl.VIDIOC_STREAMOFF.ioctl(fd, type );
	}
	
	void setFormat( V4L4JFormat fmt ) throws IOException {
		Ioctl.VIDIOC_S_FMT.ioctl( fd, fmt );
	}

	void getFormat( V4L4JFormat fmt ) throws IOException {
		Ioctl.VIDIOC_G_FMT.ioctl( fd, fmt );
	}

	void set_width_height_framerate() throws IOException {
		// TODO: set framerate

		V4L4JFormat fmt = new V4L4JFormat.ByReference(); 
		fmt.type = BufferType.V4L2_BUF_TYPE_VIDEO_CAPTURE.toInteger();
		fmt.fmt.pix.width = width;
		fmt.fmt.pix.height = height;
		fmt.fmt.pix.pixelformat = pixel_format.toInteger();
		setFormat( fmt );
	}

	void queryCapabilities( V4L4JCapability cap )
	throws IOException
	{
		Ioctl.VIDIOC_QUERYCAP.ioctl( fd, cap );
	}

	void check_capabilities() throws IOException {

		V4L4JCapability cap = new V4L4JCapability.ByReference();
		queryCapabilities( cap );
		cap_set = Capability.asSet( cap );
		
		boolean have_one = false;
		for( Capability c: at_least_one ) {
			if ( cap_set.contains( c ) ) {
				have_one = true;
				break;
			}
		}
		if ( ! have_one ) {
			throw new IOException( "device does not support at least one of the required capabilities " + at_least_one );
		}

		D( "device supports capabilities " + cap_set );
	}
	
	void enumerateFormat( V4L4JFormatDescriptor fmt )
	throws IOException
	{
		Ioctl.VIDIOC_ENUM_FMT.ioctl( fd, fmt );
	}
	
	void choose_pixfmt() {

		available_pixfmts = new ArrayList<PixelFormat>();

		V4L4JFormatDescriptor fmtdesc = new V4L4JFormatDescriptor.ByReference(); 

		D( "enumerating available pixel formats" );
		fmtdesc.type = BufferType.V4L2_BUF_TYPE_VIDEO_CAPTURE.toInteger();
		fmtdesc.index = 0;

		for( ;; ) {
			try {
				enumerateFormat( fmtdesc );
				D( "available format: " + fmtdesc );
				available_pixfmts.add( PixelFormat.toEnum( fmtdesc.pixelformat ) );
				fmtdesc.index++;
			} catch( IOException e ) {
				break;
			}
		}

		pixel_format = null;
		for ( PixelFormat pf: supported_pixfmts ) {
			if ( available_pixfmts.contains( pf ) ) {
				D( "choosing pixel format " + pf );
				pixel_format = pf;
				break;
			}
		}

		if ( null == pixel_format ) {
			throw new IllegalStateException(
				"no supported pixel formats ( " + supported_pixfmts + " ) "
				"found in available pixelf formats ( " + available_pixfmts + " )"
			);
		}
	}
	
	void requestBuffers( V4L4JRequestBuffers reqbuf )
	throws IOException
	{
		Ioctl.VIDIOC_REQBUFS.ioctl( fd, reqbuf );
	}

	void queryBuffer( V4L4JBuffer buff )
	throws IOException
	{
		Ioctl.VIDIOC_QUERYBUF.ioctl( fd, buff );
	}
	
	void map_buffers() throws IOException {
		int i;

        final int expected_framesize = pixel_format.frameSize( width, height ); 
        int actual_framesize;
        
		reqbuf = new V4L4JRequestBuffers.ByReference();

		reqbuf.type = BufferType.V4L2_BUF_TYPE_VIDEO_CAPTURE.toInteger();
		reqbuf.memory = MemoryType.V4L2_MEMORY_MMAP.toInteger();
		reqbuf.count = nbuf;

		requestBuffers( reqbuf );

		buffer = new ByteBuffer[ reqbuf.count ];

		for( i = 0; i < buffer.length; i++ ) {

			V4L4JBuffer buff = new V4L4JBuffer.ByReference();

			buff.type = reqbuf.type;
	        buff.index = i;
	        
	        queryBuffer( buff );
	        
	        actual_framesize = buff.length;
	        
	        if ( expected_framesize != actual_framesize ) {
	        	throw new IllegalStateException( "buffer " + i + " has invalid length for " + pixel_format.toFourCC() + " image of size ( " + width + ", " + height + " ). Should be " + expected_framesize + " but is " + actual_framesize );
	        }
	        
	        buffer[ i ] = fc.map( FileChannel.MapMode.READ_WRITE, buff.m.offset, buff.length );
			
			D( "mapped buffer " + i + " at offset " + buff.m.offset + " and length " + buff.length );
		}
	}
	
	void queueBuffer( V4L4JBuffer buf )
	throws IOException
	{
		Ioctl.VIDIOC_QBUF.ioctl( fd, buf );
	}

	void dequeueBuffer( V4L4JBuffer buf )
	throws IOException
	{
		Ioctl.VIDIOC_DQBUF.ioctl( fd, buf );
	}

	void get_frames() throws IOException, InterruptedException {

		V4L4JBuffer buf = new V4L4JBuffer.ByReference();

		for( int i = 0; i < nbuf; i++ ) {

			buf.index = i;
			buf.flags = 0;
			buf.memory = MemoryType.V4L2_MEMORY_MMAP.toInteger();
			buf.type = BufferType.V4L2_BUF_TYPE_VIDEO_CAPTURE.toInteger();

			I( "getting frame " + ( i + 1 ) + "/" + nbuf );

			Set<BufferFlag> flags;
			for(
				queueBuffer( buf ), queryBuffer( buf ), flags = BufferFlag.asSet( buf.flags );
				! flags.contains( BufferFlag.V4L2_BUF_FLAG_DONE );
				queryBuffer( buf ), flags = BufferFlag.asSet( buf.flags )
			) {
				V( "buffer flags: " + flags );
				Thread.sleep( 250 );
			}
			
			// this informs the linux kernel that the buffer is no longer in use
			dequeueBuffer( buf );
		}
	}

	void convert() throws IOException {
		final PixelFormat target_pixel_format = PixelFormat.V4L2_PIX_FMT_RGB24;

		D( "converting " + pixel_format.toFourCC() + " to " + target_pixel_format.toFourCC() );
		
		switch( pixel_format ) {
		case V4L2_PIX_FMT_YUYV:
			
			rgb24 = new byte[ PixelFormat.V4L2_PIX_FMT_RGB24.frameSize( width, height ) ];
			byte[] yuyv = buffer[ buffer.length - 1 ].array();
			Convert.yuyv_to_rgb24( yuyv, rgb24, width, height );
			break;

		default:
			D( "no converter available for converting " + pixel_format.toFourCC() + " to " + target_pixel_format.toFourCC() );
			break;
		}
	}

	void writePng() throws IOException {
		DataBuffer data_buffer = new DataBufferByte( rgb24, rgb24.length );
		WritableRaster raster = Raster.createInterleavedRaster( data_buffer, width, height, 3 * width, 3, new int[] { 0, 1, 2 }, new Point( 0, 0 ) );
		ColorModel cm = new ComponentColorModel(ColorModel.getRGBdefault().getColorSpace(), false, true, Transparency.OPAQUE, DataBuffer.TYPE_BYTE); 
		BufferedImage image = new BufferedImage(cm, raster, true, null);

		D( "writing output file " + ofn );
		
		ImageIO.write( image, "png", new File( ofn ) );

		D( "wrote output file " + ofn );
	}
	
	void main() throws IOException, InterruptedException {
		streamOn();
		get_frames();
		streamOff();
	}

	private static void D( Object o ) {
		if ( debug ) {
			System.out.println( "" + o );
		}
	}

	private static void V( Object o ) {
		if ( verbose ) {
			System.out.println( "" + o );
		}
	}

	private static void I( Object o ) {
		System.out.println( "" + o );
	}

	static {		
		supported_pixfmts = new TreeSet<PixelFormat>();
		supported_pixfmts.add( PixelFormat.V4L2_PIX_FMT_RGB24 );
		supported_pixfmts.add( PixelFormat.V4L2_PIX_FMT_YUYV );
		supported_pixfmts.add( PixelFormat.V4L2_PIX_FMT_YUV420 );

		at_least_one = new HashSet<Capability>();
		at_least_one.add( Capability.V4L2_CAP_VIDEO_CAPTURE );
		// at_least_one.add( Capability.V4L2_CAP_STREAMING );

		debug = true;
		verbose = false;
	}
}
