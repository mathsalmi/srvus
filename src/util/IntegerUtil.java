package util;

/**
 * Integer util methods
 * @author Matheus Salmi
 */
public class IntegerUtil {
	private IntegerUtil() { }
	
	/**
	 * Returns an Integer object holding the value of the specified String. If any error occurs, returns 0.
	 * @param obj
	 * @return
	 */
	public static Integer tryParse(String obj) {
		Integer out = 0;
		try {
			out = Integer.valueOf(obj);
		} catch(Exception e) { }
		
		return out;
	}
}
