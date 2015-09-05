/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2007-2014 InstantCom Ltd. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://raw.github.com/vnesek/instantcom-mm7/master/LICENSE.txt
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at appropriate location.
 */

package net.instantcom.mm7;

import java.io.IOException;
import java.io.OutputStream;

public class Base64Content extends BasicContent {

	private String base64Content;
	
	public Base64Content(String contentType, String contentId, String base64Content) {
		setContentType(contentType);
		setContentId(contentId);
		this.base64Content = base64Content;
	}
	
	@Override
	public void writeTo(OutputStream out, String contentId, MM7Context ctx) throws IOException {
		if (contentId == null) {
			contentId = getContentId();
		}
		StringBuilder b = new StringBuilder();
		b.append("\r\nContent-Type: ").append(getContentType());
		b.append("\r\nContent-Transfer-Encoding: base64");
		b.append("\r\nContent-Disposition: attachment; filename=\"").append(contentId).append("\"");	
		b.append("\r\nContent-ID: <").append(contentId ).append(">");
		b.append("\r\n\r\n");
		out.write(b.toString().getBytes("iso-8859-1"));
		out.write(base64Content.getBytes("utf-8"));
	}
	
}
