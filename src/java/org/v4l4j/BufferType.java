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

public enum BufferType {

	 V4L2_BUF_TYPE_VIDEO_CAPTURE,
	 V4L2_BUF_TYPE_VIDEO_OUTPUT,
	 V4L2_BUF_TYPE_VIDEO_OVERLAY,
	 V4L2_BUF_TYPE_VBI_CAPTURE,
	 V4L2_BUF_TYPE_VBI_OUTPUT,
	 V4L2_BUF_TYPE_SLICED_VBI_CAPTURE,
	 V4L2_BUF_TYPE_SLICED_VBI_OUTPUT,

	 V4L2_BUF_TYPE_VIDEO_OUTPUT_OVERLAY,
	 V4L2_BUF_TYPE_VIDEO_CAPTURE_MPLANE,
	 V4L2_BUF_TYPE_VIDEO_OUTPUT_MPLANE,
	 V4L2_BUF_TYPE_SDR_CAPTURE,

	 V4L2_BUF_TYPE_PRIVATE,
	 ;
	
	public int toInteger() {
		return toInteger( this );
	}
	
	static final TreeMap<Integer,BufferType> to = new TreeMap<Integer,BufferType>();  
	static final TreeMap<BufferType,Integer> fro = new TreeMap<BufferType,Integer>();
	
	public static int toInteger( BufferType e ) {
		return fro.get( e );
	}
	public static BufferType toEnum( int e ) {
		return to.get( e );
	}

	static {
		
		to.put( 1, V4L2_BUF_TYPE_VIDEO_CAPTURE );
		to.put( 2, V4L2_BUF_TYPE_VIDEO_OUTPUT );
		to.put( 3, V4L2_BUF_TYPE_VIDEO_OVERLAY );
		to.put( 4, V4L2_BUF_TYPE_VBI_CAPTURE );
		to.put( 5, V4L2_BUF_TYPE_VBI_OUTPUT );
		to.put( 6, V4L2_BUF_TYPE_SLICED_VBI_CAPTURE );
		to.put( 7, V4L2_BUF_TYPE_SLICED_VBI_OUTPUT );

		to.put( 8, V4L2_BUF_TYPE_VIDEO_OUTPUT_OVERLAY );
		to.put( 9, V4L2_BUF_TYPE_VIDEO_CAPTURE_MPLANE );
		to.put( 10, V4L2_BUF_TYPE_VIDEO_OUTPUT_MPLANE );
		to.put( 11, V4L2_BUF_TYPE_SDR_CAPTURE );

		to.put( 0x80, V4L2_BUF_TYPE_PRIVATE );
		
		for( Map.Entry<Integer,BufferType> e: to.entrySet() ) {
			fro.put( e.getValue(), e.getKey() );
		}
	}
}
