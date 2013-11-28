package util;

import java.nio.file.Path;

/**
 * File util methods
 * @author Matheus Salmi
 */
public class FileUtil {
	private FileUtil() { }
	
	/**
	 * Returns file extension given its name
	 * @param name
	 * @return
	 */
	public static String getExt(String name) {
		if(name != null && ! name.isEmpty()) {
			int index = name.lastIndexOf('.');
			return name.substring(index + 1, name.length());
		}
		
		return null;
	}
	
	/**
	 * Returns file extension given its path
	 * @param path
	 * @return
	 */
	public static String getExt(Path path) {
		return getExt(path.toString());
	}
	
	/**
	 * Returns file MIME type given its path
	 * @param path
	 * @return
	 */
	public static String getMimeType(Path path) {
		return MIMEUtil.findType(path);
	}
}
