package util;

import java.io.Closeable;

/**
 * Closeable classes util methods
 * @author Matheus Salmi
 */
public class CloseableUtil {
	private CloseableUtil() { }

	/**
	 * Tries to close an closeable object. It ignores any exception that might be thrown.
	 * @param obj
	 */
	public static void tryClose(Closeable obj) {
		try {
			obj.close();
		} catch(Exception e) { }
	}
}
