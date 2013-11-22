package enums;

/**
 * HTTP request method
 * @author Matheus Salmi
 */
public enum EMethod {
	GET, HEAD, POST; // TODO: add the other methods
	
	/**
	 * Find method given a string value
	 * @param method
	 * @return
	 */
	public static EMethod find(String method) {
		if(method != null && ! method.isEmpty()) {
			for(EMethod m : values()) {
				if(m.name().equals(method)) {
					return  m;
				}
			}
		}
		
		return null;
	}
}
