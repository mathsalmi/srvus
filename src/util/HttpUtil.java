package util;

/**
 * Methods for dealing with HTTP-related actions
 * @author Matheus Salmi
 */
public class HttpUtil {
	
	/**
	 * Checks whether or not a HTTP version is supported
	 * @param version
	 * @return
	 */
	public static boolean isSupported(String version) {
		return version != null && version.equals("HTTP/1.1");
	}
}