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

public enum BufferFlag {
	
	V4L2_BUF_FLAG_MAPPED,
	V4L2_BUF_FLAG_QUEUED,
	V4L2_BUF_FLAG_DONE,
	V4L2_BUF_FLAG_KEYFRAME,
	V4L2_BUF_FLAG_PFRAME,
	V4L2_BUF_FLAG_BFRAME,
	V4L2_BUF_FLAG_ERROR,
	V4L2_BUF_FLAG_TIMECODE,
	V4L2_BUF_FLAG_PREPARED,
	V4L2_BUF_FLAG_NO_CACHE_INVALIDATE,
	V4L2_BUF_FLAG_NO_CACHE_CLEAN,
	V4L2_BUF_FLAG_TIMESTAMP_MASK,
	V4L2_BUF_FLAG_TIMESTAMP_UNKNOWN,
	V4L2_BUF_FLAG_TIMESTAMP_MONOTONIC,
	V4L2_BUF_FLAG_TIMESTAMP_COPY,
	V4L2_BUF_FLAG_TSTAMP_SRC_MASK,
	V4L2_BUF_FLAG_TSTAMP_SRC_EOF,
	V4L2_BUF_FLAG_TSTAMP_SRC_SOE,
	;
	
	public int toInteger() {
		return toInteger( this );
	}
	
	static final TreeMap<Integer,BufferFlag> to = new TreeMap<Integer,BufferFlag>();  
	static final TreeMap<BufferFlag,Integer> fro = new TreeMap<BufferFlag,Integer>();
	
	public static int toInteger( BufferFlag e ) {
		return  fro.get( e );
	}
	public static BufferFlag toEnum( int e ) {
		return to.get( e );
	}

	static Set<BufferFlag> asSet( int flags ) {
		TreeSet<BufferFlag> flagset = new TreeSet<BufferFlag>();
		for( BufferFlag f: BufferFlag.values() ) {
			if ( 0 != ( flags & f.toInteger() ) ) {
				flagset.add( f );
			}
		}
		return flagset;
	}

	static {
		
		to.put( 0x00000001, V4L2_BUF_FLAG_MAPPED );
		to.put( 0x00000002, V4L2_BUF_FLAG_QUEUED );
		to.put( 0x00000004, V4L2_BUF_FLAG_DONE );
		to.put( 0x00000008, V4L2_BUF_FLAG_KEYFRAME );
		to.put( 0x00000010, V4L2_BUF_FLAG_PFRAME );
		to.put( 0x00000020, V4L2_BUF_FLAG_BFRAME );
		to.put( 0x00000040, V4L2_BUF_FLAG_ERROR );
		to.put( 0x00000100, V4L2_BUF_FLAG_TIMECODE );
		to.put( 0x00000400, V4L2_BUF_FLAG_PREPARED );
		to.put( 0x00000800, V4L2_BUF_FLAG_NO_CACHE_INVALIDATE );
		to.put( 0x00001000, V4L2_BUF_FLAG_NO_CACHE_CLEAN );
		to.put( 0x0000e000, V4L2_BUF_FLAG_TIMESTAMP_MASK );
		to.put( 0x00000000, V4L2_BUF_FLAG_TIMESTAMP_UNKNOWN );
		to.put( 0x00002000, V4L2_BUF_FLAG_TIMESTAMP_MONOTONIC );
		to.put( 0x00004000, V4L2_BUF_FLAG_TIMESTAMP_COPY );
		to.put( 0x00070000, V4L2_BUF_FLAG_TSTAMP_SRC_MASK );
		to.put( 0x00000000, V4L2_BUF_FLAG_TSTAMP_SRC_EOF );
		to.put( 0x00010000, V4L2_BUF_FLAG_TSTAMP_SRC_SOE );
		
		for( Map.Entry<Integer,BufferFlag> e: to.entrySet() ) {
			fro.put( e.getValue(), e.getKey() );
		}

		fro.put( V4L2_BUF_FLAG_TIMESTAMP_UNKNOWN, 0x00000000 );
	}
}
