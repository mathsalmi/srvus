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
}
