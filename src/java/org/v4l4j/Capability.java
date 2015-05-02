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

public enum Capability {

	V4L2_CAP_VIDEO_CAPTURE,  
	V4L2_CAP_VIDEO_OUTPUT,  
	V4L2_CAP_VIDEO_OVERLAY,  
	V4L2_CAP_VBI_CAPTURE,  
	V4L2_CAP_VBI_OUTPUT,  
	V4L2_CAP_SLICED_VBI_CAPTURE,  
	V4L2_CAP_SLICED_VBI_OUTPUT,  
	V4L2_CAP_RDS_CAPTURE,  
	V4L2_CAP_VIDEO_OUTPUT_OVERLAY,  
	V4L2_CAP_HW_FREQ_SEEK,  
	V4L2_CAP_RDS_OUTPUT,  

	V4L2_CAP_VIDEO_CAPTURE_MPLANE,
	
	V4L2_CAP_VIDEO_OUTPUT_MPLANE,
	
	V4L2_CAP_VIDEO_M2M_MPLANE,
	
	V4L2_CAP_VIDEO_M2M,

	V4L2_CAP_TUNER,  
	V4L2_CAP_AUDIO,  
	V4L2_CAP_RADIO,  
	V4L2_CAP_MODULATOR,  

	V4L2_CAP_SDR_CAPTURE,  
	V4L2_CAP_EXT_PIX_FORMAT,  

	V4L2_CAP_READWRITE,  
	V4L2_CAP_ASYNCIO,  
	V4L2_CAP_STREAMING,  

	V4L2_CAP_DEVICE_CAPS,  
	;
	
	public int toInteger() {
		return toInteger( this );
	}
	
	static final TreeMap<Integer,Capability> to = new TreeMap<Integer,Capability>();  
	static final TreeMap<Capability,Integer> fro = new TreeMap<Capability,Integer>();
	
	public static int toInteger( Capability e ) {
		return fro.get( e );
	}
	public static Capability toEnum( int e ) {
		return to.get( e );
	}

	static Set<Capability> asSet( V4L4JCapability caps ) {
		TreeSet<Capability> capset = new TreeSet<Capability>();
		for( Capability c: Capability.values() ) {
			if ( 0 != ( caps.capabilities & c.toInteger() ) ) {
				capset.add( c );
			}
		}
		return capset;
	}
	
	static {
		to.put( 0x00000001, V4L2_CAP_VIDEO_CAPTURE );  
		to.put( 0x00000002, V4L2_CAP_VIDEO_OUTPUT );  
		to.put( 0x00000004, V4L2_CAP_VIDEO_OVERLAY );  
		to.put( 0x00000010, V4L2_CAP_VBI_CAPTURE );  
		to.put( 0x00000020, V4L2_CAP_VBI_OUTPUT );  
		to.put( 0x00000040, V4L2_CAP_SLICED_VBI_CAPTURE );  
		to.put( 0x00000080, V4L2_CAP_SLICED_VBI_OUTPUT );  
		to.put( 0x00000100, V4L2_CAP_RDS_CAPTURE );  
		to.put( 0x00000200, V4L2_CAP_VIDEO_OUTPUT_OVERLAY );  
		to.put( 0x00000400, V4L2_CAP_HW_FREQ_SEEK );  
		to.put( 0x00000800, V4L2_CAP_RDS_OUTPUT );  
		
		to.put( 0x00001000, V4L2_CAP_VIDEO_CAPTURE_MPLANE );
		
		to.put( 0x00002000, V4L2_CAP_VIDEO_OUTPUT_MPLANE );
		
		to.put( 0x00004000, V4L2_CAP_VIDEO_M2M_MPLANE );
		
		to.put( 0x00008000, V4L2_CAP_VIDEO_M2M );
		
		to.put( 0x00010000, V4L2_CAP_TUNER );  
		to.put( 0x00020000, V4L2_CAP_AUDIO );  
		to.put( 0x00040000, V4L2_CAP_RADIO );  
		to.put( 0x00080000, V4L2_CAP_MODULATOR );  
		
		to.put( 0x00100000, V4L2_CAP_SDR_CAPTURE );  
		to.put( 0x00200000, V4L2_CAP_EXT_PIX_FORMAT );  
		
		to.put( 0x01000000, V4L2_CAP_READWRITE );  
		to.put( 0x02000000, V4L2_CAP_ASYNCIO );  
		to.put( 0x04000000, V4L2_CAP_STREAMING );  
		
		to.put( 0x80000000, V4L2_CAP_DEVICE_CAPS );  
		
		for( Map.Entry<Integer,Capability> e: to.entrySet() ) {
			fro.put( e.getValue(), e.getKey() );
		}
	}
}
