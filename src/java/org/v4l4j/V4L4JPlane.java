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

public class V4L4JPlane extends Structure {

	public static class ByReference extends V4L4JPlane implements Structure.ByReference {};

	public static class Reference extends Union {
		public static class ByReference extends Reference implements Union.ByReference {};
		
		public int mem_offset;
		public NativeLong userptr;
		public int fd;
	}

	public int bytesused;
	public int length;
	public Reference m;
	public int data_offset;
	public int[] reserved = new int[ 11 ];
	
	@Override
	public String toString() {
		return getClass().getName() + '@' + Integer.toHexString(hashCode()) + ", " +
			"bytesused: " + bytesused +
			", " +
			"length: " + length +
			", " +
			"data_offset: " + Integer.toHexString( data_offset ) +
			"";
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected List getFieldOrder() {
		return Arrays.asList( new String[] { "bytesused", "length", "m", "data_offset", "reserved", } );
	}
	
	@Override
	public void write() {
		m.setType( NativeLong.class );
		super.write();
	}
	
	@Override
    public void read() {
        super.read();
        m.setType( NativeLong.class );
        m.read();
	}
}
