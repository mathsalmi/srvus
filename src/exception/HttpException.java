package exception;

import enums.EStatusCode;

/**
 * Thrown when there is a problem while processing HTTP message
 * @author Matheus Salmi
 */
public class HttpException extends RuntimeException {
	private static final long serialVersionUID = -3723812818550227259L;
	private EStatusCode statusCode = null;
	
	public HttpException(EStatusCode statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * @return the statusCode
	 */
	public EStatusCode getStatusCode() {
		return statusCode;
	}
	
	@Override
	public String toString() {
		return statusCode.getCode() + " " + statusCode.getDescription();
	}
}
