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

public enum FieldType {

	V4L2_FIELD_ANY,
	V4L2_FIELD_NONE,
	V4L2_FIELD_TOP,
	V4L2_FIELD_BOTTOM,
	V4L2_FIELD_INTERLACED,
	V4L2_FIELD_SEQ_TB,
	V4L2_FIELD_SEQ_BT,
	V4L2_FIELD_ALTERNATE,
	V4L2_FIELD_INTERLACED_TB,
	V4L2_FIELD_INTERLACED_BT,
	;
	
	public int toInteger() {
		return toInteger( this );
	}
	
	static final TreeMap<Integer,FieldType> to = new TreeMap<Integer,FieldType>();  
	static final TreeMap<FieldType,Integer> fro = new TreeMap<FieldType,Integer>();
	
	public static int toInteger( FieldType e ) {
		return fro.get( e );
	}
	public static FieldType toEnum( int e ) {
		return to.get( e );
	}

	static Set<FieldType> asSet( V4L4JCapability caps ) {
		TreeSet<FieldType> capset = new TreeSet<FieldType>();
		for( FieldType c: FieldType.values() ) {
			if ( 0 != ( caps.capabilities & c.toInteger() ) ) {
				capset.add( c );
			}
		}
		return capset;
	}
	
	static {
		int i = 0;
		to.put( i++, V4L2_FIELD_ANY );
		to.put( i++, V4L2_FIELD_NONE );
		to.put( i++, V4L2_FIELD_TOP );
		to.put( i++, V4L2_FIELD_BOTTOM );
		to.put( i++, V4L2_FIELD_INTERLACED );
		to.put( i++, V4L2_FIELD_SEQ_TB );
		to.put( i++, V4L2_FIELD_SEQ_BT );
		to.put( i++, V4L2_FIELD_ALTERNATE );
		to.put( i++, V4L2_FIELD_INTERLACED_TB );
		to.put( i++, V4L2_FIELD_INTERLACED_BT );
		
		for( Map.Entry<Integer,FieldType> e: to.entrySet() ) {
			fro.put( e.getValue(), e.getKey() );
		}
	}
}
