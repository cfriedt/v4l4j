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

import com.sun.jna.*;

public class V4L4JRequestBuffers extends Structure {

	public static class ByReference extends V4L4JRequestBuffers implements Structure.ByReference {};

	public int count;
	public int type;
	public int memory;
	public int[] reserved = new int[2];

	@Override
	public String toString() {
		String s = "";
		// XXX: for some reason, this throws a NullPointerException
		// s +=  getClass().getName() + "@" + Integer.toHexString( hashCode() );
		s +=  getClass().getName() + "@" + Integer.toHexString( 0x12345678 );
		s += ", ";
		s += "count: " + count;
		s += ", ";
		s += "type: " + BufferType.toEnum( type );
		s += ", ";
		s += "memory: " + MemoryType.toEnum( memory );
		return s;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getFieldOrder() {
		return Arrays.asList( new String[] { "count", "type", "memory", "reserved", } );
	}
}
