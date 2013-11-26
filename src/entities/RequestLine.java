package entities;

import java.net.URI;

import enums.EMethod;

/**
 * Represent the request line
 * 
 * @author Matheus Salmi
 */
public class RequestLine {
	private EMethod method;
	private URI requestUri;
	private String httpVersion;
	
	/**
	 * @return the method
	 */
	public EMethod getMethod() {
		return method;
	}

	/**
	 * @param method the method to set
	 */
	public void setMethod(EMethod method) {
		this.method = method;
	}

	/**
	 * @return the requestUri
	 */
	public URI getRequestUri() {
		return requestUri;
	}

	/**
	 * @param requestUri the requestUri to set
	 */
	public void setRequestUri(URI requestUri) {
		this.requestUri = requestUri;
	}

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
	 * Shows the request-line according to RFC 2616
	 * (@url http://www.w3.org/Protocols/rfc2616/rfc2616-sec5.html#sec5.1)
	 */
	@Override
	public String toString() {
		return getMethod() + " " + getRequestUri() + " " + getHttpVersion() + "\r\n";
	}
}
