package entities;

import enums.EStatusCode;

/**
 * Represents the status line
 * @author Matheus Salmi
 */
public class StatusLine {
	private String httpVersion;
	private EStatusCode statusCode;
	
	/**
	 * @return the httpVersion
	 */
	public String getHttpVersion() {
		return httpVersion;
	}
	/**
	 * @param httpVersion the httpVersion to set
	 */
	public void setHttpVersion(String httpVersion) {
		this.httpVersion = httpVersion;
	}
	/**
	 * @return the statusCode
	 */
	public EStatusCode getStatusCode() {
		return statusCode;
	}
	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(EStatusCode statusCode) {
		this.statusCode = statusCode;
	}
	
	/**
	 * Shows the status-line according to RFC 2616
	 * (@url http://www.w3.org/Protocols/rfc2616/rfc2616-sec6.html#sec6.1)
	 */
	@Override
	public String toString() {
		return httpVersion + " " + statusCode.getCode() + " " + statusCode.getDescription() + "\r\n";
	}
}
