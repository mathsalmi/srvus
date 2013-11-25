package messages;

import java.io.OutputStream;

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
	private StatusLine statusLine = null;
	private ResponseFields responseFields = null;
	private String body = null;
	
	private Response() {
		this.statusLine = new StatusLine();
		this.responseFields = new ResponseFields();
	}
	
	public Response(OutputStream outstream) {
		this();
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
	public void setStatusLine(EStatusCode statuscode) {
		if(statuscode == null) {
			throw new IllegalArgumentException("Status code cannot be null.");
		}
		statusLine.setHttpVersion(HttpUtil.currentVersion());
		statusLine.setStatusCode(statuscode);
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
	 * Deletes an element from the header
	 * @param name
	 */
	public void deleteHeaderField(String name) {
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
}