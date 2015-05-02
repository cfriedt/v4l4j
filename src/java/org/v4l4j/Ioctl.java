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

import java.io.*;
import java.util.*;

import org.jruby.ext.posix.*;

import com.sun.jna.*;

public enum Ioctl {

	VIDIOC_QUERYCAP,
	VIDIOC_RESERVED,
	VIDIOC_ENUM_FMT,
	VIDIOC_G_FMT,
	VIDIOC_S_FMT,
	VIDIOC_REQBUFS,
	VIDIOC_QUERYBUF,
	VIDIOC_QBUF,
	VIDIOC_DQBUF,
	VIDIOC_STREAMON,
	VIDIOC_STREAMOFF,
	;

	public int toInteger() {
		return toInteger( this );
	}
	
	static final TreeMap<Integer,Ioctl> to = new TreeMap<Integer,Ioctl>();  
	static final TreeMap<Ioctl,Integer> fro = new TreeMap<Ioctl,Integer>();
	
	public static int toInteger( Ioctl e ) {
		return  fro.get( e );
	}
	public static Ioctl toEnum( int e ) {
		return to.get( e );
	}

	static {
		to.put( _IOR( 'V',  0, ( new V4L4JCapability.ByReference() ).size() ), VIDIOC_QUERYCAP );
		to.put( _IO( 'V',  1 ), VIDIOC_RESERVED );
		to.put( _IOWR( 'V',  2, ( new V4L4JFormatDescriptor.ByReference() ).size() ), VIDIOC_ENUM_FMT );
		to.put( _IOWR( 'V',  4, ( new V4L4JFormat.ByReference() ).size() ), VIDIOC_G_FMT );
		to.put( _IOWR('V',  5,  ( new V4L4JFormat.ByReference() ).size() ), VIDIOC_S_FMT );
		to.put( _IOWR( 'V',  8, ( new V4L4JRequestBuffers.ByReference() ).size() ), VIDIOC_REQBUFS );
		to.put( _IOWR( 'V',  9, ( new V4L4JBuffer.ByReference() ).size() ), VIDIOC_QUERYBUF );
		to.put( _IOWR( 'V', 15, ( new V4L4JBuffer.ByReference() ).size() ), VIDIOC_QBUF );
		to.put( _IOWR( 'V', 17, ( new V4L4JBuffer.ByReference() ).size() ), VIDIOC_DQBUF );
		to.put( _IOW( 'V', 18, ( new IntPointer.ByReference() ).size() ), VIDIOC_STREAMON );
		to.put( _IOW( 'V', 19, ( new IntPointer.ByReference() ).size() ), VIDIOC_STREAMOFF );

		for( Map.Entry<Integer,Ioctl> e: to.entrySet() ) {
			fro.put( e.getValue(), e.getKey() );
		}
	}

	//
	// To extend the usual integer / enum mapping to also support NativeLong queries
	//
	
	private static final long unsigned_int_mask = (1L << Integer.SIZE) - 1;

	public NativeLong toNativeLong() {
		return toNativeLong( this );
	}
	
	public static NativeLong toNativeLong( Ioctl e ) {
		return new NativeLong( unsigned_int_mask & fro.get( e ) );
	}
	public static Ioctl toEnum( NativeLong e ) {
		return to.get( e.intValue() );
	}
	
	//
	// V4L2 IOCTLS
	//
	
	static final POSIXHandler handler; 
	static final POSIX posix;

	public int ioctl( FileDescriptor fd, Object... arg )
	throws IOException
	{
		int r = -1;
		r = posix.ioctl( fd, this.toNativeLong(), arg );
		if ( r < 0 ) {
			throw new IOException( "ioctl " + this + " failed with return value " + r + " and errno " + posix.errno() );
		}
		return r;
	}
	
	static {
		handler = new V4L4JPOSIXHandler();
		posix = POSIXFactory.getPOSIX( handler, true );
	}
	
	static final int _IOC_NRBITS = 8;
	static final int _IOC_TYPEBITS = 8;
	static final int _IOC_SIZEBITS = 14;
	static final int _IOC_DIRBITS = 2;

	static final int _IOC_NRMASK = ((1 << _IOC_NRBITS)-1);
	static final int _IOC_TYPEMASK	= ((1 << _IOC_TYPEBITS)-1);
	static final int _IOC_SIZEMASK	= ((1 << _IOC_SIZEBITS)-1);
	static final int _IOC_DIRMASK	= ((1 << _IOC_DIRBITS)-1);

	static final int _IOC_NRSHIFT = 0;
	static final int _IOC_TYPESHIFT = _IOC_NRSHIFT + _IOC_NRBITS;
	static final int _IOC_SIZESHIFT = _IOC_TYPESHIFT + _IOC_TYPEBITS;
	static final int _IOC_DIRSHIFT = _IOC_SIZESHIFT + _IOC_SIZEBITS;

	static final int _IOC_NONE = 0;
	static final int _IOC_WRITE = 1;
	static final int _IOC_READ = 2;
	
	static int _IOC( int dir, int type, int nr, int size ) {
		int r = 0;
		r |= ( dir & _IOC_DIRMASK ) << _IOC_DIRSHIFT;
		r |= ( type & _IOC_TYPEMASK ) << _IOC_TYPESHIFT;
		r |= ( nr & _IOC_NRMASK ) << _IOC_NRSHIFT;
		r |= ( size & _IOC_SIZEMASK ) << _IOC_SIZESHIFT;

		return r;
	}
	static int _IO( int type, int nr ) {
		return _IOC( _IOC_NONE, type , nr , 0 );
	}
	static int _IOWR( int type, int nr, int size ) {
		return _IOC( _IOC_READ | _IOC_WRITE, type, nr, size );
	}

	static int _IOR( int type, int nr, int size ) {
		return _IOC( _IOC_READ, type, nr, size );
	}

	static int _IOW( int type, int nr, int size ) {
		return _IOC( _IOC_WRITE, type, nr, size );
	}
/*
	public static final int VIDIOC_G_FBUF = _IOR('V', 10, struct v4l2_framebuffer)
	public static final int VIDIOC_S_FBUF = _IOW('V', 11, struct v4l2_framebuffer)
	public static final int VIDIOC_OVERLAY = _IOW('V', 14, int)

	public static final int VIDIOC_QBUF = _IOWR('V', 15, struct v4l2_buffer)
	public static final int VIDIOC_EXPBUF = _IOWR('V', 16, struct v4l2_exportbuffer)
	public static final int VIDIOC_DQBUF = _IOWR('V', 17, struct v4l2_buffer)
	public static final int VIDIOC_STREAMON = _IOW('V', 18, int)
	public static final int VIDIOC_STREAMOFF = _IOW('V', 19, int)
	public static final int VIDIOC_G_PARM = _IOWR('V', 21, struct v4l2_streamparm)
	public static final int VIDIOC_S_PARM = _IOWR('V', 22, struct v4l2_streamparm)
	public static final int VIDIOC_G_STD = _IOR('V', 23, v4l2_std_id)
	public static final int VIDIOC_S_STD = _IOW('V', 24, v4l2_std_id)
	public static final int VIDIOC_ENUMSTD = _IOWR('V', 25, struct v4l2_standard)
	public static final int VIDIOC_ENUMINPUT = _IOWR('V', 26, struct v4l2_input)
	public static final int VIDIOC_G_CTRL = _IOWR('V', 27, struct v4l2_control)
	public static final int VIDIOC_S_CTRL = _IOWR('V', 28, struct v4l2_control)
	public static final int VIDIOC_G_TUNER = _IOWR('V', 29, struct v4l2_tuner)
	public static final int VIDIOC_S_TUNER = _IOW('V', 30, struct v4l2_tuner)
	public static final int VIDIOC_G_AUDIO = _IOR('V', 33, struct v4l2_audio)
	public static final int VIDIOC_S_AUDIO = _IOW('V', 34, struct v4l2_audio)
	public static final int VIDIOC_QUERYCTRL = _IOWR('V', 36, struct v4l2_queryctrl)
	public static final int VIDIOC_QUERYMENU = _IOWR('V', 37, struct v4l2_querymenu)
	public static final int VIDIOC_G_INPUT = _IOR('V', 38, int)
	public static final int VIDIOC_S_INPUT = _IOWR('V', 39, int)
	public static final int VIDIOC_G_EDID = _IOWR('V', 40, struct v4l2_edid)
	public static final int VIDIOC_S_EDID = _IOWR('V', 41, struct v4l2_edid)
	public static final int VIDIOC_G_OUTPUT = _IOR('V', 46, int)
	public static final int VIDIOC_S_OUTPUT = _IOWR('V', 47, int)
	public static final int VIDIOC_ENUMOUTPUT = _IOWR('V', 48, struct v4l2_output)
	public static final int VIDIOC_G_AUDOUT = _IOR('V', 49, struct v4l2_audioout)
	public static final int VIDIOC_S_AUDOUT = _IOW('V', 50, struct v4l2_audioout)
	public static final int VIDIOC_G_MODULATOR = _IOWR('V', 54, struct v4l2_modulator)
	public static final int VIDIOC_S_MODULATOR = _IOW('V', 55, struct v4l2_modulator)
	public static final int VIDIOC_G_FREQUENCY = _IOWR('V', 56, struct v4l2_frequency)
	public static final int VIDIOC_S_FREQUENCY = _IOW('V', 57, struct v4l2_frequency)
	public static final int VIDIOC_CROPCAP = _IOWR('V', 58, struct v4l2_cropcap)
	public static final int VIDIOC_G_CROP = _IOWR('V', 59, struct v4l2_crop)
	public static final int VIDIOC_S_CROP = _IOW('V', 60, struct v4l2_crop)
	public static final int VIDIOC_G_JPEGCOMP = _IOR('V', 61, struct v4l2_jpegcompression)
	public static final int VIDIOC_S_JPEGCOMP = _IOW('V', 62, struct v4l2_jpegcompression)
	public static final int VIDIOC_QUERYSTD = _IOR('V', 63, v4l2_std_id)
	public static final int VIDIOC_TRY_FMT = _IOWR('V', 64, struct v4l2_format)
	public static final int VIDIOC_ENUMAUDIO = _IOWR('V', 65, struct v4l2_audio)
	public static final int VIDIOC_ENUMAUDOUT = _IOWR('V', 66, struct v4l2_audioout)
	public static final int VIDIOC_G_PRIORITY = _IOR('V', 67, __u32) // enum v4l2_priority
	public static final int VIDIOC_S_PRIORITY = _IOW('V', 68, __u32) // enum v4l2_priority
	public static final int VIDIOC_G_SLICED_VBI_CAP = _IOWR('V', 69, struct v4l2_sliced_vbi_cap)
	public static final int VIDIOC_LOG_STATUS = _IO('V', 70)
	public static final int VIDIOC_G_EXT_CTRLS = _IOWR('V', 71, struct v4l2_ext_controls)
	public static final int VIDIOC_S_EXT_CTRLS = _IOWR('V', 72, struct v4l2_ext_controls)
	public static final int VIDIOC_TRY_EXT_CTRLS = _IOWR('V', 73, struct v4l2_ext_controls)
	public static final int VIDIOC_ENUM_FRAMESIZES = _IOWR('V', 74, struct v4l2_frmsizeenum)
	public static final int VIDIOC_ENUM_FRAMEINTERVALS = _IOWR('V', 75, struct v4l2_frmivalenum)
	public static final int VIDIOC_G_ENC_INDEX = _IOR('V', 76, struct v4l2_enc_idx)
	public static final int VIDIOC_ENCODER_CMD = _IOWR('V', 77, struct v4l2_encoder_cmd)
	public static final int VIDIOC_TRY_ENCODER_CMD = _IOWR('V', 78, struct v4l2_encoder_cmd)

	// Experimental, meant for debugging, testing and internal use.
	// Only implemented if CONFIG_VIDEO_ADV_DEBUG is defined.
	// You must be root to use these ioctls. Never use these in applications!
	public static final int	VIDIOC_DBG_S_REGISTER = _IOW('V', 79, struct v4l2_dbg_register)
	public static final int	VIDIOC_DBG_G_REGISTER = _IOWR('V', 80, struct v4l2_dbg_register)

	public static final int VIDIOC_S_HW_FREQ_SEEK = _IOW('V', 82, struct v4l2_hw_freq_seek)

	public static final int	VIDIOC_S_DV_TIMINGS = _IOWR('V', 87, struct v4l2_dv_timings)
	public static final int	VIDIOC_G_DV_TIMINGS = _IOWR('V', 88, struct v4l2_dv_timings)
	public static final int	VIDIOC_DQEVENT = _IOR('V', 89, struct v4l2_event)
	public static final int	VIDIOC_SUBSCRIBE_EVENT = _IOW('V', 90, struct v4l2_event_subscription)
	public static final int	VIDIOC_UNSUBSCRIBE_EVENT = _IOW('V', 91, struct v4l2_event_subscription)

	// Experimental, the below two ioctls may change over the next couple of kernel
	// versions
	public static final int VIDIOC_CREATE_BUFS = _IOWR('V', 92, struct v4l2_create_buffers)
	public static final int VIDIOC_PREPARE_BUF = _IOWR('V', 93, struct v4l2_buffer)

	// Experimental selection API
	public static final int VIDIOC_G_SELECTION = _IOWR('V', 94, struct v4l2_selection)
	public static final int VIDIOC_S_SELECTION = _IOWR('V', 95, struct v4l2_selection)

	// Experimental, these two ioctls may change over the next couple of kernel
	// versions.
	public static final int VIDIOC_DECODER_CMD = _IOWR('V', 96, struct v4l2_decoder_cmd)
	public static final int VIDIOC_TRY_DECODER_CMD = _IOWR('V', 97, struct v4l2_decoder_cmd)

	// Experimental, these three ioctls may change over the next couple of kernel
	// versions.
	public static final int VIDIOC_ENUM_DV_TIMINGS = _IOWR('V', 98, struct v4l2_enum_dv_timings)
	public static final int VIDIOC_QUERY_DV_TIMINGS = _IOR('V', 99, struct v4l2_dv_timings)
	public static final int VIDIOC_DV_TIMINGS_CAP = _IOWR('V', 100, struct v4l2_dv_timings_cap)

	// Experimental, this ioctl may change over the next couple of kernel
	// versions.
	public static final int VIDIOC_ENUM_FREQ_BANDS = _IOWR('V', 101, struct v4l2_frequency_band)

	// Experimental, meant for debugging, testing and internal use.
	// Never use these in applications!
	public static final int VIDIOC_DBG_G_CHIP_INFO = _IOWR('V', 102, struct v4l2_dbg_chip_info)

	public static final int VIDIOC_QUERY_EXT_CTRL = _IOWR('V', 103, struct v4l2_query_ext_ctrl)

	// Reminder: when adding new ioctls please add support for them to
	// drivers/media/video/v4l2-compat-ioctl32.c as well!

	public static final int BASE_VIDIOC_PRIVATE	192		// 192-255 are private
	*/
}
