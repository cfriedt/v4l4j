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

public class Convert {

	private Convert() {}
	
	static int clip( int x ) {
		if ( x > 255 )
			return 255;
		if ( x < 0 )
			return 0;
		return x;
	}

	static void yuv_to_rgb24( byte[] yuv, byte[] rgb24, int w, int h ) {

		int expected_length;
		int actual_length;

		actual_length = yuv.length;
		expected_length = PixelFormat.V4L2_PIX_FMT_RGB24.frameSize( w, h );
		if ( actual_length < expected_length ) {
			throw new IllegalArgumentException( "invalid input buffer length " + actual_length + " for yuv image of size ( " + w + ", " + h + " ). Should be " + expected_length  );
		}

		actual_length = rgb24.length;
		expected_length = PixelFormat.V4L2_PIX_FMT_RGB24.frameSize( w, h );
		if ( actual_length < expected_length ) {
			throw new IllegalArgumentException( "invalid output buffer length " + actual_length + " for rgb24 image of size ( " + w + ", " + h + " ). Should be " + expected_length );
		}
		
		int y, u, v;
		int c, d, e;
		int r, g, b;

		int i;
		for( i = 0; i < w * h * 3; i += 3 ) {
			y = 0xff & yuv[ i + 0 ];
			u = 0xff & yuv[ i + 1 ];
			v = 0xff & yuv[ i + 2 ];
		    c = y -  16;
		    d = u - 128;
		    e = v - 128;
	        r = ( 298 * c           + 409 * e + 128 ) >> 8;
	        g = ( 298 * c - 100 * d - 208 * e + 128 ) >> 8;
	        b = ( 298 * c + 516 * d           + 128 ) >> 8;
	        rgb24[ i + 0 ] = (byte) clip( r );
	        rgb24[ i + 1 ] = (byte) clip( g );
	        rgb24[ i + 2 ] = (byte) clip( b );
		}
	}

	static void yuv420_to_yuv( byte[] in, byte[] out, int w, int h) {
/*
		boolean even = true;
		int r, c;
		byte *y, *u, *v;
		y = in;
		u = & in[ w * h ];
		v = & in[ w * h + w * h / 4 ];
		for( r = 0; r < h; r++ ) {
			for( c = 0; c < w; c += 2 ) {
				out[ 3 * ( r * w +   c       ) ] = *y;
				out[ 3 * ( r * w + ( c + 1 ) ) ] = *( y + 1 );
				y += 2;
				if( even ) {
			        out[ 3 * ( r * w + c ) + 1 ] =
			        out[ 3 * ( r * w + ( c + 1 ) ) + 1 ] =
			        out[ 3 * ( ( r + 1 ) * w + c ) + 1 ] =
			        out[ 3 * ( ( r + 1 ) * w + ( c + 1 ) ) + 1 ] = *u;
			        u++;
				} else {
			        out[ 3 * ( r * w + c ) + 2 ] =
			        out[ 3 * ( r * w + ( c + 1 ) ) + 2 ] =
			        out[ 3 * ( ( r - 1 ) * w + c ) + 2 ] =
			        out[ 3 * ( ( r - 1 ) * w + ( c + 1 ) ) + 2 ] = *v;
			        v++;
				}
			}
			even = !even;
		}
*/		
	}

	static void yuv420_to_rgb24( byte[] yuv420, byte[] out, int w, int h ) {
		/*
		if ( ! yuv ) {
			errno = ENOMEM;
			E("failed to allocate frame");
			goto just_return;
		}
		out = yuv_to_rgb24( yuv, w, h );
		free( yuv );
	just_return:
		return out;
		*/
	}
	static void yuyv_to_yuv( byte[] yuyv, byte[] yuv, int w, int h ) {
		
		int expected_length;
		int actual_length;

		actual_length = yuyv.length;
		expected_length = PixelFormat.V4L2_PIX_FMT_YUYV.frameSize( w, h );
		if ( actual_length < expected_length ) {
			throw new IllegalArgumentException( "invalid input buffer length " + actual_length + " for yuyv image of size ( " + w + ", " + h + " ). Should be " + expected_length  );
		}

		actual_length = yuv.length;
		expected_length = PixelFormat.V4L2_PIX_FMT_RGB24.frameSize( w, h );
		if ( actual_length < expected_length ) {
			throw new IllegalArgumentException( "invalid output buffer length " + actual_length + " for yuv image of size ( " + w + ", " + h + " ). Should be " + expected_length );
		}
		
		for( int i = 0, j = 0; i < w * h * 3; i += 6, j += 4 ) {
			yuv[ i + 0 ] = yuyv[ j + 0 ];
			yuv[ i + 1 ] = yuyv[ j + 1 ];
			yuv[ i + 2 ] = yuyv[ j + 3 ];
			yuv[ i + 3 ] = yuyv[ j + 2 ];
			yuv[ i + 4 ] = yuyv[ j + 1 ];
			yuv[ i + 5 ] = yuyv[ j + 3 ];
		}
	}
	static void yuyv_to_rgb24( byte[] yuyv, byte[] rgb24, int w, int h ) {

		int expected_length;
		int actual_length;

		actual_length = yuyv.length;
		expected_length = PixelFormat.V4L2_PIX_FMT_YUYV.frameSize( w, h );
		if ( actual_length < expected_length ) {
			throw new IllegalArgumentException( "invalid input buffer length " + actual_length + " for yuyv image of size ( " + w + ", " + h + " ). Should be " + expected_length  );
		}

		actual_length = rgb24.length;
		expected_length = PixelFormat.V4L2_PIX_FMT_RGB24.frameSize( w, h );
		if ( actual_length < expected_length ) {
			throw new IllegalArgumentException( "invalid output buffer length " + actual_length + " for rgb24 image of size ( " + w + ", " + h + " ). Should be " + expected_length );
		}

		yuyv_to_yuv( yuyv, rgb24, w, h );
		yuv_to_rgb24( rgb24, rgb24, w, h );
	}
}
