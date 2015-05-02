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

import java.util.*;

public enum PixelFormat {

	V4L2_PIX_FMT_RGB332,
	V4L2_PIX_FMT_RGB444,
	V4L2_PIX_FMT_ARGB444, 
	V4L2_PIX_FMT_XRGB444, 
	V4L2_PIX_FMT_RGB555, 
	V4L2_PIX_FMT_ARGB555, 
	V4L2_PIX_FMT_XRGB555, 
	V4L2_PIX_FMT_RGB565, 
	V4L2_PIX_FMT_RGB555X, 
	V4L2_PIX_FMT_ARGB555X, 
	V4L2_PIX_FMT_XRGB555X, 
	V4L2_PIX_FMT_RGB565X, 
	V4L2_PIX_FMT_BGR666, 
	V4L2_PIX_FMT_BGR24, 
	V4L2_PIX_FMT_RGB24, 
	V4L2_PIX_FMT_BGR32, 
	V4L2_PIX_FMT_ABGR32, 
	V4L2_PIX_FMT_XBGR32, 
	V4L2_PIX_FMT_RGB32, 
	V4L2_PIX_FMT_ARGB32, 
	V4L2_PIX_FMT_XRGB32, 

	V4L2_PIX_FMT_GREY, 
	V4L2_PIX_FMT_Y4, 
	V4L2_PIX_FMT_Y6, 
	V4L2_PIX_FMT_Y10, 
	V4L2_PIX_FMT_Y12, 
	V4L2_PIX_FMT_Y16, 

	V4L2_PIX_FMT_Y10BPACK, 

	V4L2_PIX_FMT_PAL8, 
	
	V4L2_PIX_FMT_UV8, 

	V4L2_PIX_FMT_YVU410, 
	V4L2_PIX_FMT_YVU420, 
	V4L2_PIX_FMT_YUYV, 
	V4L2_PIX_FMT_YYUV, 
	V4L2_PIX_FMT_YVYU, 
	V4L2_PIX_FMT_UYVY, 
	V4L2_PIX_FMT_VYUY, 
	V4L2_PIX_FMT_YUV422P, 
	V4L2_PIX_FMT_YUV411P, 
	V4L2_PIX_FMT_Y41P, 
	V4L2_PIX_FMT_YUV444, 
	V4L2_PIX_FMT_YUV555, 
	V4L2_PIX_FMT_YUV565, 
	V4L2_PIX_FMT_YUV32, 
	V4L2_PIX_FMT_YUV410, 
	V4L2_PIX_FMT_YUV420, 
	V4L2_PIX_FMT_HI240, 
	V4L2_PIX_FMT_HM12, 
	V4L2_PIX_FMT_M420, 

	V4L2_PIX_FMT_NV12, 
	V4L2_PIX_FMT_NV21, 
	V4L2_PIX_FMT_NV16, 
	V4L2_PIX_FMT_NV61, 
	V4L2_PIX_FMT_NV24, 
	V4L2_PIX_FMT_NV42, 
	
	V4L2_PIX_FMT_NV12M, 
	V4L2_PIX_FMT_NV21M, 
	V4L2_PIX_FMT_NV16M, 
	V4L2_PIX_FMT_NV61M, 
	V4L2_PIX_FMT_NV12MT, 
	V4L2_PIX_FMT_NV12MT_16X16, 
	
	V4L2_PIX_FMT_YUV420M, 
	V4L2_PIX_FMT_YVU420M, 
	
	V4L2_PIX_FMT_SBGGR8, 
	V4L2_PIX_FMT_SGBRG8, 
	V4L2_PIX_FMT_SGRBG8, 
	V4L2_PIX_FMT_SRGGB8, 
	V4L2_PIX_FMT_SBGGR10, 
	V4L2_PIX_FMT_SGBRG10, 
	V4L2_PIX_FMT_SGRBG10, 
	V4L2_PIX_FMT_SRGGB10, 
	V4L2_PIX_FMT_SBGGR12, 
	V4L2_PIX_FMT_SGBRG12, 
	V4L2_PIX_FMT_SGRBG12, 
	V4L2_PIX_FMT_SRGGB12, 
		
	V4L2_PIX_FMT_SBGGR10ALAW8,
	V4L2_PIX_FMT_SGBRG10ALAW8,
	V4L2_PIX_FMT_SGRBG10ALAW8,
	V4L2_PIX_FMT_SRGGB10ALAW8,
		
	V4L2_PIX_FMT_SBGGR10DPCM8,
	V4L2_PIX_FMT_SGBRG10DPCM8,
	V4L2_PIX_FMT_SGRBG10DPCM8,
	V4L2_PIX_FMT_SRGGB10DPCM8,

	V4L2_PIX_FMT_SBGGR16, 

	V4L2_PIX_FMT_MJPEG, 
	V4L2_PIX_FMT_JPEG, 
	V4L2_PIX_FMT_DV, 
	V4L2_PIX_FMT_MPEG, 
	V4L2_PIX_FMT_H264, 
	V4L2_PIX_FMT_H264_NO_SC, 
	V4L2_PIX_FMT_H264_MVC, 
	V4L2_PIX_FMT_H263, 
	V4L2_PIX_FMT_MPEG1, 
	V4L2_PIX_FMT_MPEG2, 
	V4L2_PIX_FMT_MPEG4, 
	V4L2_PIX_FMT_XVID, 
	V4L2_PIX_FMT_VC1_ANNEX_G, 
	V4L2_PIX_FMT_VC1_ANNEX_L, 
	V4L2_PIX_FMT_VP8, 

	V4L2_PIX_FMT_CPIA1, 
	V4L2_PIX_FMT_WNVA, 
	V4L2_PIX_FMT_SN9C10X, 
	V4L2_PIX_FMT_SN9C20X_I420, 
	V4L2_PIX_FMT_PWC1, 
	V4L2_PIX_FMT_PWC2, 
	V4L2_PIX_FMT_ET61X251, 
	V4L2_PIX_FMT_SPCA501, 
	V4L2_PIX_FMT_SPCA505, 
	V4L2_PIX_FMT_SPCA508, 
	V4L2_PIX_FMT_SPCA561, 
	V4L2_PIX_FMT_PAC207, 
	V4L2_PIX_FMT_MR97310A, 
	V4L2_PIX_FMT_JL2005BCD, 
	V4L2_PIX_FMT_SN9C2028, 
	V4L2_PIX_FMT_SQ905C, 
	V4L2_PIX_FMT_PJPG, 
	V4L2_PIX_FMT_OV511, 
	V4L2_PIX_FMT_OV518, 
	V4L2_PIX_FMT_STV0680, 
	V4L2_PIX_FMT_TM6000, 
	V4L2_PIX_FMT_CIT_YYVYUY, 
	V4L2_PIX_FMT_KONICA420, 
	V4L2_PIX_FMT_JPGL, 
	V4L2_PIX_FMT_SE401, 
	V4L2_PIX_FMT_S5C_UYVY_JPG, 

	V4L2_SDR_FMT_CU8, 
	V4L2_SDR_FMT_CU16LE, 
	V4L2_SDR_FMT_CS8, 
	V4L2_SDR_FMT_CS14LE, 
	V4L2_SDR_FMT_RU12LE,
	;
	
	public int toInteger() {
		return toInteger( this );
	}
	
	static final int V4L2_PIX_FMT_PRIV_MAGIC;
	static final int V4L2_PIX_FMT_FLAG_PREMUL_ALPHA;
	
	public String toFourCC() {
		final int e = toInteger();
		final int a = 0;
		final int b = 1;
		final int c = 2;
		final int d = 3;
		byte[] fourcc = new byte[4];
		fourcc[ a ] = (byte) ( ( e >>>  0 ) & 0xff );
	 	fourcc[ b ] = (byte) ( ( e >>>  8 ) & 0xff );
	 	fourcc[ c ] = (byte) ( ( e >>> 16 ) & 0xff );
	 	fourcc[ d ] = (byte) ( ( e >>> 24 ) & 0xff );
	 	return new String( fourcc );
	}
	
	static int v4l2_fourcc( byte a, byte b, byte c, byte d ) {
		return
			( ( (int) a & 0xff ) << 0  ) |
			( ( (int) b & 0xff ) << 8  ) |
			( ( (int) c & 0xff ) << 16 ) |
			( ( (int) d & 0xff ) << 24 );
	}
	static int v4l2_fourcc_be( byte a, byte b, byte c, byte d ) {
		return v4l2_fourcc( a, b, c, d ) | ( 1 << 31 );
	}
	static int v4l2_fourcc( char a, char b, char c, char d ) {
		return v4l2_fourcc( (byte) a, (byte) b, (byte) c, (byte) d );
	}
	static int v4l2_fourcc_be( char a, char b, char c, char d ) {
		return v4l2_fourcc_be( (byte) a, (byte) b, (byte) c, (byte) d );
	}
	
	static final TreeMap<Integer,PixelFormat> to = new TreeMap<Integer,PixelFormat>();  
	static final TreeMap<PixelFormat,Integer> fro = new TreeMap<PixelFormat,Integer>();
	
	public static int toInteger( PixelFormat e ) {
		return fro.get( e );
	}
	public static PixelFormat toEnum( int e ) {
		return to.get( e );
	}
	
	public static int FRAMESIZE_VARIABLE = -2;
	public static int frameSize( PixelFormat fmt, int width, int height )
	throws NoSuchElementException
	{
		int r = -1;
		switch( fmt ) {
		case V4L2_PIX_FMT_RGB24:
			r = 3 * width * height;
			break;
		case V4L2_PIX_FMT_YUYV:
			r = 2 * width * height;
			break;
		case V4L2_PIX_FMT_YUV420:
			r = width * height * 3 / 2;
			break;
		default:
			break;
		}
		if ( -1 == r ) {
			throw new NoSuchElementException();
		}
		return r;
	}
	public int frameSize( int width, int height )
	throws NoSuchElementException
	{
		return frameSize( this, width, height );
	}
	
	static {
		to.put( v4l2_fourcc( 'R', 'G', 'B', '1'  ), V4L2_PIX_FMT_RGB332 ); 
		to.put( v4l2_fourcc( 'R', '4', '4', '4' ), V4L2_PIX_FMT_RGB444 ); 
		to.put( v4l2_fourcc( 'A', 'R', '1', '2' ), V4L2_PIX_FMT_ARGB444 ); 
		to.put( v4l2_fourcc( 'X', 'R', '1', '2' ), V4L2_PIX_FMT_XRGB444 ); 
		to.put( v4l2_fourcc( 'R', 'G', 'B', 'O' ), V4L2_PIX_FMT_RGB555 ); 
		to.put( v4l2_fourcc( 'A', 'R', '1', '5' ), V4L2_PIX_FMT_ARGB555 ); 
		to.put( v4l2_fourcc( 'X', 'R', '1', '5' ), V4L2_PIX_FMT_XRGB555 ); 
		to.put( v4l2_fourcc( 'R', 'G', 'B', 'P' ), V4L2_PIX_FMT_RGB565 ); 
		to.put( v4l2_fourcc( 'R', 'G', 'B', 'Q' ), V4L2_PIX_FMT_RGB555X ); 
		to.put( v4l2_fourcc_be( 'A', 'R', '1', '5' ), V4L2_PIX_FMT_ARGB555X ); 
		to.put( v4l2_fourcc_be( 'X', 'R', '1', '5' ), V4L2_PIX_FMT_XRGB555X ); 
		to.put( v4l2_fourcc( 'R', 'G', 'B', 'R' ), V4L2_PIX_FMT_RGB565X ); 
		to.put( v4l2_fourcc( 'B', 'G', 'R', 'H' ), V4L2_PIX_FMT_BGR666 ); 
		to.put( v4l2_fourcc( 'B', 'G', 'R', '3' ), V4L2_PIX_FMT_BGR24 ); 
		to.put( v4l2_fourcc( 'R', 'G', 'B', '3' ), V4L2_PIX_FMT_RGB24 ); 
		to.put( v4l2_fourcc( 'B', 'G', 'R', '4' ), V4L2_PIX_FMT_BGR32 ); 
		to.put( v4l2_fourcc( 'A', 'R', '2', '4' ), V4L2_PIX_FMT_ABGR32 ); 
		to.put( v4l2_fourcc( 'X', 'R', '2', '4' ), V4L2_PIX_FMT_XBGR32 ); 
		to.put( v4l2_fourcc( 'R', 'G', 'B', '4' ), V4L2_PIX_FMT_RGB32 ); 
		to.put( v4l2_fourcc( 'B', 'A', '2', '4' ), V4L2_PIX_FMT_ARGB32 ); 
		to.put( v4l2_fourcc( 'B', 'X', '2', '4' ), V4L2_PIX_FMT_XRGB32 ); 
		
		to.put( v4l2_fourcc( 'G', 'R', 'E', 'Y' ), V4L2_PIX_FMT_GREY ); 
		to.put( v4l2_fourcc( 'Y', '0', '4', ' ' ), V4L2_PIX_FMT_Y4 ); 
		to.put( v4l2_fourcc( 'Y', '0', '6', ' ' ), V4L2_PIX_FMT_Y6 ); 
		to.put( v4l2_fourcc( 'Y', '1', '0', ' ' ), V4L2_PIX_FMT_Y10 ); 
		to.put( v4l2_fourcc( 'Y', '1', '2', ' ' ), V4L2_PIX_FMT_Y12 ); 
		to.put( v4l2_fourcc( 'Y', '1', '6', ' ' ), V4L2_PIX_FMT_Y16 ); 

		to.put( v4l2_fourcc( 'Y', '1', '0', 'B' ), V4L2_PIX_FMT_Y10BPACK ); 
		
		to.put( v4l2_fourcc( 'P', 'A', 'L', '8' ), V4L2_PIX_FMT_PAL8 ); 
		
		to.put( v4l2_fourcc( 'U', 'V', '8', ' ' ), V4L2_PIX_FMT_UV8 ); 

		to.put( v4l2_fourcc( 'Y', 'V', 'U', '9' ), V4L2_PIX_FMT_YVU410 ); 
		to.put( v4l2_fourcc( 'Y', 'V', '1', '2' ), V4L2_PIX_FMT_YVU420 ); 
		to.put( v4l2_fourcc( 'Y', 'U', 'Y', 'V' ), V4L2_PIX_FMT_YUYV ); 
		to.put( v4l2_fourcc( 'Y', 'Y', 'U', 'V' ), V4L2_PIX_FMT_YYUV ); 
		to.put( v4l2_fourcc( 'Y', 'V', 'Y', 'U' ), V4L2_PIX_FMT_YVYU ); 
		to.put( v4l2_fourcc( 'U', 'Y', 'V', 'Y' ), V4L2_PIX_FMT_UYVY ); 
		to.put( v4l2_fourcc( 'V', 'Y', 'U', 'Y' ), V4L2_PIX_FMT_VYUY ); 
		to.put( v4l2_fourcc( '4', '2', '2', 'P' ), V4L2_PIX_FMT_YUV422P ); 
		to.put( v4l2_fourcc( '4', '1', '1', 'P' ), V4L2_PIX_FMT_YUV411P ); 
		to.put( v4l2_fourcc( 'Y', '4', '1', 'P' ), V4L2_PIX_FMT_Y41P ); 
		to.put( v4l2_fourcc( 'Y', '4', '4', '4' ), V4L2_PIX_FMT_YUV444 ); 
		to.put( v4l2_fourcc( 'Y', 'U', 'V', 'O' ), V4L2_PIX_FMT_YUV555 ); 
		to.put( v4l2_fourcc( 'Y', 'U', 'V', 'P' ), V4L2_PIX_FMT_YUV565 ); 
		to.put( v4l2_fourcc( 'Y', 'U', 'V', '4' ), V4L2_PIX_FMT_YUV32 ); 
		to.put( v4l2_fourcc( 'Y', 'U', 'V', '9' ), V4L2_PIX_FMT_YUV410 ); 
		to.put( v4l2_fourcc( 'Y', 'U', '1', '2' ), V4L2_PIX_FMT_YUV420 ); 
		to.put( v4l2_fourcc( 'H', 'I', '2', '4' ), V4L2_PIX_FMT_HI240 ); 
		to.put( v4l2_fourcc( 'H', 'M', '1', '2' ), V4L2_PIX_FMT_HM12 ); 
		to.put( v4l2_fourcc( 'M', '4', '2', '0' ), V4L2_PIX_FMT_M420 ); 

		to.put( v4l2_fourcc( 'N', 'V', '1', '2' ), V4L2_PIX_FMT_NV12 ); 
		to.put( v4l2_fourcc( 'N', 'V', '2', '1' ), V4L2_PIX_FMT_NV21 ); 
		to.put( v4l2_fourcc( 'N', 'V', '1', '6' ), V4L2_PIX_FMT_NV16 ); 
		to.put( v4l2_fourcc( 'N', 'V', '6', '1' ), V4L2_PIX_FMT_NV61 ); 
		to.put( v4l2_fourcc( 'N', 'V', '2', '4' ), V4L2_PIX_FMT_NV24 ); 
		to.put( v4l2_fourcc( 'N', 'V', '4', '2' ), V4L2_PIX_FMT_NV42 ); 

		to.put( v4l2_fourcc( 'N', 'M', '1', '2' ), V4L2_PIX_FMT_NV12M ); 
		to.put( v4l2_fourcc( 'N', 'M', '2', '1' ), V4L2_PIX_FMT_NV21M ); 
		to.put( v4l2_fourcc( 'N', 'M', '1', '6' ), V4L2_PIX_FMT_NV16M ); 
		to.put( v4l2_fourcc( 'N', 'M', '6', '1' ), V4L2_PIX_FMT_NV61M ); 
		to.put( v4l2_fourcc( 'T', 'M', '1', '2' ), V4L2_PIX_FMT_NV12MT ); 
		to.put( v4l2_fourcc( 'V', 'M', '1', '2' ), V4L2_PIX_FMT_NV12MT_16X16 ); 
		
		to.put( v4l2_fourcc( 'Y', 'M', '1', '2' ), V4L2_PIX_FMT_YUV420M ); 
		to.put( v4l2_fourcc( 'Y', 'M', '2', '1' ), V4L2_PIX_FMT_YVU420M ); 
		
		to.put( v4l2_fourcc( 'B', 'A', '8', '1' ), V4L2_PIX_FMT_SBGGR8 ); 
		to.put( v4l2_fourcc( 'G', 'B', 'R', 'G' ), V4L2_PIX_FMT_SGBRG8 ); 
		to.put( v4l2_fourcc( 'G', 'R', 'B', 'G' ), V4L2_PIX_FMT_SGRBG8 ); 
		to.put( v4l2_fourcc( 'R', 'G', 'G', 'B' ), V4L2_PIX_FMT_SRGGB8 ); 
		to.put( v4l2_fourcc( 'B', 'G', '1', '0' ), V4L2_PIX_FMT_SBGGR10 ); 
		to.put( v4l2_fourcc( 'G', 'B', '1', '0' ), V4L2_PIX_FMT_SGBRG10 ); 
		to.put( v4l2_fourcc( 'B', 'A', '1', '0' ), V4L2_PIX_FMT_SGRBG10 ); 
		to.put( v4l2_fourcc( 'R', 'G', '1', '0' ), V4L2_PIX_FMT_SRGGB10 ); 
		to.put( v4l2_fourcc( 'B', 'G', '1', '2' ), V4L2_PIX_FMT_SBGGR12 ); 
		to.put( v4l2_fourcc( 'G', 'B', '1', '2' ), V4L2_PIX_FMT_SGBRG12 ); 
		to.put( v4l2_fourcc( 'B', 'A', '1', '2' ), V4L2_PIX_FMT_SGRBG12 ); 
		to.put( v4l2_fourcc( 'R', 'G', '1', '2' ), V4L2_PIX_FMT_SRGGB12 ); 
			
		to.put( v4l2_fourcc( 'a', 'B', 'A', '8' ), V4L2_PIX_FMT_SBGGR10ALAW8 );
		to.put( v4l2_fourcc( 'a', 'G', 'A', '8' ), V4L2_PIX_FMT_SGBRG10ALAW8 );
		to.put( v4l2_fourcc( 'a', 'g', 'A', '8' ), V4L2_PIX_FMT_SGRBG10ALAW8 );
		to.put( v4l2_fourcc( 'a', 'R', 'A', '8' ), V4L2_PIX_FMT_SRGGB10ALAW8 );
			
		to.put( v4l2_fourcc( 'b', 'B', 'A', '8' ), V4L2_PIX_FMT_SBGGR10DPCM8 );
		to.put( v4l2_fourcc( 'b', 'G', 'A', '8' ), V4L2_PIX_FMT_SGBRG10DPCM8 );
		to.put( v4l2_fourcc( 'B', 'D', '1', '0' ), V4L2_PIX_FMT_SGRBG10DPCM8 );
		to.put( v4l2_fourcc( 'b', 'R', 'A', '8' ), V4L2_PIX_FMT_SRGGB10DPCM8 );

		to.put( v4l2_fourcc( 'B', 'Y', 'R', '2' ), V4L2_PIX_FMT_SBGGR16 ); 
		
		to.put( v4l2_fourcc( 'M', 'J', 'P', 'G' ), V4L2_PIX_FMT_MJPEG ); 
		to.put( v4l2_fourcc( 'J', 'P', 'E', 'G' ), V4L2_PIX_FMT_JPEG ); 
		to.put( v4l2_fourcc( 'd', 'v', 's', 'd' ), V4L2_PIX_FMT_DV ); 
		to.put( v4l2_fourcc( 'M', 'P', 'E', 'G' ), V4L2_PIX_FMT_MPEG ); 
		to.put( v4l2_fourcc( 'H', '2', '6', '4' ), V4L2_PIX_FMT_H264 ); 
		to.put( v4l2_fourcc( 'A', 'V', 'C', '1' ), V4L2_PIX_FMT_H264_NO_SC ); 
		to.put( v4l2_fourcc( 'M', '2', '6', '4' ), V4L2_PIX_FMT_H264_MVC ); 
		to.put( v4l2_fourcc( 'H', '2', '6', '3' ), V4L2_PIX_FMT_H263 ); 
		to.put( v4l2_fourcc( 'M', 'P', 'G', '1' ), V4L2_PIX_FMT_MPEG1 ); 
		to.put( v4l2_fourcc( 'M', 'P', 'G', '2' ), V4L2_PIX_FMT_MPEG2 ); 
		to.put( v4l2_fourcc( 'M', 'P', 'G', '4' ), V4L2_PIX_FMT_MPEG4 ); 
		to.put( v4l2_fourcc( 'X', 'V', 'I', 'D' ), V4L2_PIX_FMT_XVID ); 
		to.put( v4l2_fourcc( 'V', 'C', '1', 'G' ), V4L2_PIX_FMT_VC1_ANNEX_G ); 
		to.put( v4l2_fourcc( 'V', 'C', '1', 'L' ), V4L2_PIX_FMT_VC1_ANNEX_L ); 
		to.put( v4l2_fourcc( 'V', 'P', '8', '0' ), V4L2_PIX_FMT_VP8 ); 

		to.put( v4l2_fourcc( 'C', 'P', 'I', 'A' ), V4L2_PIX_FMT_CPIA1 ); 
		to.put( v4l2_fourcc( 'W', 'N', 'V', 'A' ), V4L2_PIX_FMT_WNVA ); 
		to.put( v4l2_fourcc( 'S', '9', '1', '0' ), V4L2_PIX_FMT_SN9C10X ); 
		to.put( v4l2_fourcc( 'S', '9', '2', '0' ), V4L2_PIX_FMT_SN9C20X_I420 ); 
		to.put( v4l2_fourcc( 'P', 'W', 'C', '1' ), V4L2_PIX_FMT_PWC1 ); 
		to.put( v4l2_fourcc( 'P', 'W', 'C', '2' ), V4L2_PIX_FMT_PWC2 ); 
		to.put( v4l2_fourcc( 'E', '6', '2', '5' ), V4L2_PIX_FMT_ET61X251 ); 
		to.put( v4l2_fourcc( 'S', '5', '0', '1' ), V4L2_PIX_FMT_SPCA501 ); 
		to.put( v4l2_fourcc( 'S', '5', '0', '5' ), V4L2_PIX_FMT_SPCA505 ); 
		to.put( v4l2_fourcc( 'S', '5', '0', '8' ), V4L2_PIX_FMT_SPCA508 ); 
		to.put( v4l2_fourcc( 'S', '5', '6', '1' ), V4L2_PIX_FMT_SPCA561 ); 
		to.put( v4l2_fourcc( 'P', '2', '0', '7' ), V4L2_PIX_FMT_PAC207 ); 
		to.put( v4l2_fourcc( 'M', '3', '1', '0' ), V4L2_PIX_FMT_MR97310A ); 
		to.put( v4l2_fourcc( 'J', 'L', '2', '0' ), V4L2_PIX_FMT_JL2005BCD ); 
		to.put( v4l2_fourcc( 'S', 'O', 'N', 'X' ), V4L2_PIX_FMT_SN9C2028 ); 
		to.put( v4l2_fourcc( '9', '0', '5', 'C' ), V4L2_PIX_FMT_SQ905C ); 
		to.put( v4l2_fourcc( 'P', 'J', 'P', 'G' ), V4L2_PIX_FMT_PJPG ); 
		to.put( v4l2_fourcc( 'O', '5', '1', '1' ), V4L2_PIX_FMT_OV511 ); 
		to.put( v4l2_fourcc( 'O', '5', '1', '8' ), V4L2_PIX_FMT_OV518 ); 
		to.put( v4l2_fourcc( 'S', '6', '8', '0' ), V4L2_PIX_FMT_STV0680 ); 
		to.put( v4l2_fourcc( 'T', 'M', '6', '0' ), V4L2_PIX_FMT_TM6000 ); 
		to.put( v4l2_fourcc( 'C', 'I', 'T', 'V' ), V4L2_PIX_FMT_CIT_YYVYUY ); 
		to.put( v4l2_fourcc( 'K', 'O', 'N', 'I' ), V4L2_PIX_FMT_KONICA420 ); 
		to.put( v4l2_fourcc( 'J', 'P', 'G', 'L' ), V4L2_PIX_FMT_JPGL ); 
		to.put( v4l2_fourcc( 'S', '4', '0', '1' ), V4L2_PIX_FMT_SE401 ); 
		to.put( v4l2_fourcc( 'S', '5', 'C', 'I' ), V4L2_PIX_FMT_S5C_UYVY_JPG ); 

		to.put( v4l2_fourcc( 'C', 'U', '0', '8' ), V4L2_SDR_FMT_CU8 ); 
		to.put( v4l2_fourcc( 'C', 'U', '1', '6' ), V4L2_SDR_FMT_CU16LE ); 
		to.put( v4l2_fourcc( 'C', 'S', '0', '8' ), V4L2_SDR_FMT_CS8 ); 
		to.put( v4l2_fourcc( 'C', 'S', '1', '4' ), V4L2_SDR_FMT_CS14LE ); 
		to.put( v4l2_fourcc( 'R', 'U', '1', '2' ), V4L2_SDR_FMT_RU12LE ); 
		
		for( Map.Entry<Integer,PixelFormat> e: to.entrySet() ) {
			fro.put( e.getValue(), e.getKey() );
		}
		
		V4L2_PIX_FMT_PRIV_MAGIC = 0xfeedcafe;
		V4L2_PIX_FMT_FLAG_PREMUL_ALPHA = 0x00000001;
	}
}
