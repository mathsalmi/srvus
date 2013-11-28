package messages;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import util.FileUtil;
import util.HttpUtil;
import entities.ResponseFields;
import entities.StatusLine;
import enums.EStatusCode;
import exception.HttpException;

/**
 * Represents an HTTP response.
 * @author Matheus Salmi
 */
public class Response {
	private OutputStream out = null;
	private final StatusLine statusLine = new StatusLine();
	private final ResponseFields responseFields = new ResponseFields();
	private byte[] body = null;
	
	public Response(OutputStream outstream) {
		this.out = outstream;
	}
	
	/**
	 * Returns the status-line
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
	 * Returns body
	 * @return the body
	 */
	public byte[] getBody() {
		return body;
	}

	/**
	 * Sets body and some body-related header fields
	 * @param body the body to set
	 */
	public void setBody(byte[] body) {
		this.body = body;
		
		// set length
		if(body != null) {
			this.addHeaderField("content-length", String.valueOf(body.length));
			
			// if content-type is still unknown, use default
			if(this.getHeader().get("content-type") == null) {
				this.addHeaderField("content-type", "application/octet-stream");
			}
		} else {
			this.removeHeaderField("content-length");
			this.removeHeaderField("content-type");
		}
	}	
	
	/**
	 * Sets body and some body-related header fields
	 * @param body the body to set
	 */
	public void setBody(String body) {
		setBody(body.getBytes());
	}
	
	/**
	 * Sets body and some body-related header fields
	 * @param body the body to set
	 */
	public void setBody(Path body) throws HttpException {
		try {
			setBody(Files.readAllBytes(body));
			
			String type = FileUtil.getMimeType(body);
			if(type != null) {
				this.addHeaderField("content-type", type);
			}
		} catch (IOException | SecurityException e) {
			throw new HttpException(EStatusCode.C_404);
		} catch(OutOfMemoryError e) {
			throw new HttpException(EStatusCode.C_413);
		} catch(Exception e) {
			throw new HttpException(EStatusCode.C_500);
		}
	}
	
	/**
	 * Sends the response
	 * @throws IOException 
	 */
	public void send() throws IOException {
		out.write(getStatusLine().toString().getBytes());
		out.write((this.responseFields.toString() + "\r\n").getBytes());
		out.write(getBody());
		out.flush();
	}
}