package messages;

import java.io.OutputStream;
import java.io.PrintWriter;

import util.HttpUtil;
import entities.ResponseFields;
import entities.StatusLine;
import enums.EStatusCode;

/**
 * Represents an HTTP response.
 * @author Matheus Salmi
 */
public class Response {
	private OutputStream outstream = null;
	private final StatusLine statusLine = new StatusLine();
	private final ResponseFields responseFields = new ResponseFields();
	private String body = null;
	
	public Response(OutputStream outstream) {
		this.outstream = outstream;
	}
	
	/**
	 * Return status-line
	 * @return the statusLine
	 */
	public StatusLine getStatusLine() {
		return statusLine;
	}
	
	/**
	 * Adds the code to the status-line
	 * @param statuscode
	 */
	public void setStatusCode(EStatusCode statuscode) {
		if(statuscode == null) {
			throw new IllegalArgumentException("Status code cannot be null.");
		}
		statusLine.setHttpVersion(HttpUtil.currentVersion());
		statusLine.setStatusCode(statuscode);
	}
	
	/**
	 * Returns the header fields
	 * @return
	 */
	public ResponseFields getHeader() {
		return this.responseFields;
	}
	
	/**
	 * Adds an element to the header
	 * @param name
	 * @param value
	 */
	public void addHeaderField(String name, String value) {
		this.responseFields.put(name, value);
	}
	
	/**
	 * Removes an element from the header
	 * @param name
	 */
	public void removeHeaderField(String name) {
		this.responseFields.remove(name);
	}

	/**
	 * Return body
	 * @return the body
	 */
	public String getBody() {
		return body;
	}

	/**
	 * Set body
	 * @param body the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}
	
	/**
	 * Send the response
	 */
	public void send() {
		PrintWriter out = new PrintWriter(this.outstream);
		out.write(getStatusLine().toString());
		out.write(this.responseFields.toString() + "\r\n");
		out.write(getBody());
		out.flush();
	}
}