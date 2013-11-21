package enums;

/**
 * HTTP response status code
 * @author Matheus Salmi
 */
public enum EStatusCode {
	C_200(200, "OK");
	// TODO: add the other codes
	
	// fields
	private int code;
	private String description;
	
	EStatusCode(int code, String description) {
		this.code = code;
		this.description = description;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getDescription() {
		return description;
	}
}
