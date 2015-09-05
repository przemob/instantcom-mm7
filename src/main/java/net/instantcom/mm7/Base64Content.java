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
