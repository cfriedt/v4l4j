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

public enum Colorspace {

	V4L2_COLORSPACE_SMPTE170M,
	V4L2_COLORSPACE_SMPTE240M,
	V4L2_COLORSPACE_REC709,
	V4L2_COLORSPACE_BT878,
	V4L2_COLORSPACE_470_SYSTEM_M,
	V4L2_COLORSPACE_470_SYSTEM_BG,
	V4L2_COLORSPACE_JPEG,
	V4L2_COLORSPACE_SRGB,
	;
	
	public int toInteger() {
		return toInteger( this );
	}
	
	static final TreeMap<Integer,Colorspace> to = new TreeMap<Integer,Colorspace>();  
	static final TreeMap<Colorspace,Integer> fro = new TreeMap<Colorspace,Integer>();
	
	public static int toInteger( Colorspace e ) {
		return fro.get( e );
	}
	public static Colorspace toEnum( int e ) {
		return to.get( e );
	}

	static Set<Colorspace> asSet( V4L4JCapability caps ) {
		TreeSet<Colorspace> capset = new TreeSet<Colorspace>();
		for( Colorspace c: Colorspace.values() ) {
			if ( 0 != ( caps.capabilities & c.toInteger() ) ) {
				capset.add( c );
			}
		}
		return capset;
	}
	
	static {

		int i = 0;

		to.put( i++, V4L2_COLORSPACE_SMPTE170M );
		to.put( i++, V4L2_COLORSPACE_SMPTE240M );
		to.put( i++, V4L2_COLORSPACE_REC709 );
		to.put( i++, V4L2_COLORSPACE_BT878 );
		to.put( i++, V4L2_COLORSPACE_470_SYSTEM_M );
		to.put( i++, V4L2_COLORSPACE_470_SYSTEM_BG );
		to.put( i++, V4L2_COLORSPACE_JPEG );
		to.put( i++, V4L2_COLORSPACE_SRGB );
		
		for( Map.Entry<Integer,Colorspace> e: to.entrySet() ) {
			fro.put( e.getValue(), e.getKey() );
		}
	}
}
