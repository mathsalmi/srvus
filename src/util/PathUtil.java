package util;

import java.net.URL;

/**
 * Contains the string paths for important directories
 * @author Matheus Salmi
 */
public class PathUtil {
	// TODO: add to config: 1) log; 2) errors; 3) sites folder;
	// directories
	public static final String ROOT_DIR = getRoot();
	public static final String SITES_DIR = ROOT_DIR + "/" + ServerConfig.get("sites.dirname");
	public static final String ETC_DIR = ROOT_DIR + "/etc";
	
	// files
	public static final String MIME_FILE = ETC_DIR + "/mime.types";
	public static final String SRVUS_CONFIG = ETC_DIR + "/srvus.conf";
	
	
	/**
	 * Returns the root directory absolute path
	 */
	private static String getRoot() {
		String filename = PathUtil.class.getName().replace('.', '/') + ".class";
		URL classUrl = PathUtil.class.getClassLoader().getResource(filename);
		
		return classUrl.getPath().replaceFirst("/", "").replaceFirst("/bin/" + filename, "");
	}
}