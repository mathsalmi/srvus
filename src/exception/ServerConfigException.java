package exception;

/**
 * Thrown if there is a problem with Srvus config file
 * @author Matheus Salmi
 */
public class ServerConfigException extends RuntimeException {
	private static final long serialVersionUID = -3723812818550227259L;
	
	public ServerConfigException() {
		
	}
	
	public ServerConfigException(String message) {
		super(message);
	}
	
	public ServerConfigException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ServerConfigException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
	public ServerConfigException(Throwable cause) {
		super(cause);
	}
}