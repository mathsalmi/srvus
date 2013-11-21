package entities;

import enums.EMethod;

/**
 * Represent the request line
 * 
 * @author Matheus Salmi
 */
public class RequestLine {
	private EMethod method;
	private String requestUri;
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
	public String getRequestUri() {
		return requestUri;
	}
	/**
	 * @param requestUri the requestUri to set
	 */
	public void setRequestUri(String requestUri) {
		this.requestUri = requestUri;
	}
	/**
	 * @return the uri
	 */
	public String getUri() {
		return requestUri;
	}
	/**
	 * @param uri the uri to set
	 */
	public void setUri(String uri) {
		this.requestUri = uri;
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
}
