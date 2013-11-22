package util;

/**
 * Methods for dealing with HTTP-related actions
 * @author Matheus Salmi
 */
public class HttpUtil {
	private static final String VERSION = "HTTP/1.1";
	
	/**
	 * Checks whether or not a HTTP version is supported
	 * @param version
	 * @return
	 */
	public static boolean isSupported(String version) {
		return version != null && version.equals(currentVersion());
	}
	
	/**
	 * Returns the version of this HTTP Server
	 * @return
	 */
	public static String currentVersion() {
		return VERSION;
	}
}