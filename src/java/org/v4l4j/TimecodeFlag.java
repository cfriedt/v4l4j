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

public enum TimecodeFlag {
	
	V4L2_TC_FLAG_DROPFRAME,
	V4L2_TC_FLAG_COLORFRAME,
	V4L2_TC_USERBITS_field,
	V4L2_TC_USERBITS_USERDEFINED,
	V4L2_TC_USERBITS_8BITCHARS,
	;
	
	public int toInteger() {
		return toInteger( this );
	}
	
	static final TreeMap<Integer,TimecodeFlag> to = new TreeMap<Integer,TimecodeFlag>();  
	static final TreeMap<TimecodeFlag,Integer> fro = new TreeMap<TimecodeFlag,Integer>();
	
	public static int toInteger( TimecodeFlag e ) {
		return  fro.get( e );
	}
	public static TimecodeFlag toEnum( int e ) {
		return to.get( e );
	}

	static Set<TimecodeFlag> asSet( int flags ) {
		TreeSet<TimecodeFlag> flagset = new TreeSet<TimecodeFlag>();
		for( TimecodeFlag f: TimecodeFlag.values() ) {
			if ( 0 != ( flags & f.toInteger() ) ) {
				flagset.add( f );
			}
		}
		return flagset;
	}

	static {
		
		to.put( 0x0001, V4L2_TC_FLAG_DROPFRAME );
		to.put( 0x0002, V4L2_TC_FLAG_COLORFRAME );
		to.put( 0x000C, V4L2_TC_USERBITS_field );
		to.put( 0x0000, V4L2_TC_USERBITS_USERDEFINED );
		to.put( 0x0008, V4L2_TC_USERBITS_8BITCHARS );
		
		for( Map.Entry<Integer,TimecodeFlag> e: to.entrySet() ) {
			fro.put( e.getValue(), e.getKey() );
		}
	}
}
