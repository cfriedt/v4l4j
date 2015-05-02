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

public class V4L4JJPEGCompression extends Structure {

	public static class ByReference extends V4L4JJPEGCompression implements Structure.ByReference {};

	public int quality;
	public int APPn;
	public int APP_len;
	public byte[] APP_data = new byte[ 60 ];
	public int COM_len;
	public byte[] COM_data = new byte[ 60 ];
	public int jpeg_markers;

	@Override
	public String toString() {
		return getClass().getName() + '@' + Integer.toHexString(hashCode()) + ", "
			"quality: " + quality
			", "
			"APPn: " + APPn
			", "
			"APP_len: " + APP_len
			", "
			"APP_data: " + Arrays.asList( APP_data )
			", "
			"COM_len: " + COM_len
			", "
			"COM_data: " + Arrays.asList( COM_data )
			", "
			"minutes: " + Integer.toHexString( jpeg_markers )
			"";
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected List getFieldOrder() {
		return Arrays.asList( new String[] { "quality", "APPn", "APP_len", "APP_data", "COM_len", "COM_data", "jpeg_markers",  } );
	}
}
