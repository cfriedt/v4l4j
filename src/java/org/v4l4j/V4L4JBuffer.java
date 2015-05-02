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

public class V4L4JBuffer extends Structure {

	public static class ByReference extends V4L4JBuffer implements Structure.ByReference {};

	public static class MemoryLocation extends Union {
		public static class ByReference extends MemoryLocation implements Union.ByReference {};

		public int offset;
		public NativeLong userptr;
		public Pointer planes;
		public int fd;
	}

	public int index;
	public int type;
	public int bytesused;
	public int flags;
	public int field;
	public Timeval timestamp;
	public V4L4JTimecode timecode;
	public int sequence;
	public int memory;
	public MemoryLocation m;
	public int length;
	public int reserved2;
	public int reserved;
	
	@Override
	public String toString() {
		String s = "";
		s += getClass().getName() + '@' + Integer.toHexString( hashCode() );
		s += ", ";
		s += "index: " + index;
		s += ", ";
		s += "type: " + BufferType.toEnum( type );
		s += ", ";
		s += "bytesused: " + bytesused;
		s += ", ";
		s += "flags: " + BufferFlag.asSet( flags );
		s += ", ";
		s += "field: " + FieldType.toEnum( field );
		s += ", ";
		s += "timestamp: " + timestamp;
		s += ", ";
		s += "timecode: " + timecode;
		s += ", ";
		s += "sequence: " + sequence;
		s += ", ";
		
		MemoryType mt = MemoryType.toEnum( memory );

		s += "memory: " + mt;
		s += ", ";
		s += "m: " + mt;
		s += ", ";
		s += "length: " + length;
		return s;
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected List getFieldOrder() {
		return Arrays.asList( new String[] { "index", "type", "bytesused", "flags", "field", "timestamp", "timecode", "sequence", "memory", "m", "length", "reserved2", "reserved", } );
	}
	
	@Override
	public void write() {
        MemoryType memory_type = MemoryType.toEnum( memory );
        if ( null == memory_type ) {
        	m.setType( Pointer.class );
        } else {
	        switch( memory_type ) {
	        case V4L2_MEMORY_USERPTR:
	        	m.setType( NativeLong.class );
	        	break;
	        case V4L2_MEMORY_MMAP:
	        case V4L2_MEMORY_DMABUF:
	        	m.setType( int.class );
	        	break;
	        case V4L2_MEMORY_OVERLAY:
	        default:
	        	m.setType( Pointer.class );
	        	break;
	        }
        }
        super.write();
	}
	
	@Override
    public void read() {
        super.read();
        MemoryType memory_type = MemoryType.toEnum( memory );
        if ( null == memory_type ) {
        	m.setType( Pointer.class );
        } else {
	        switch( memory_type ) {
	        case V4L2_MEMORY_USERPTR:
	        	m.setType( NativeLong.class );
	        	break;
	        case V4L2_MEMORY_MMAP:
	        case V4L2_MEMORY_DMABUF:
	        	m.setType( int.class );
	        	break;
	        case V4L2_MEMORY_OVERLAY:
	        default:
	        	m.setType( Pointer.class );
	        	break;
	        }
        }
        m.read();
	}
}
