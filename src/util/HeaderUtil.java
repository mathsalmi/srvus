package util;

import entities.HeaderFields;

/**
 * Methods for dealing with HTTP headers
 * @author Matheus Salmi
 * @deprecated
 */
public class HeaderUtil {
	
	/**
	 * Parses a line as a header
	 * 
	 * @param line line to be parsed
	 * @param clzz class to be converted
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends HeaderFields> T parse(String line, Class<? extends HeaderFields> clzz) {
		try {
			HeaderFields output = clzz.newInstance();
			String[] tokens = line.split(":");
			if(tokens.length == 2) {
				output.put(tokens[0].trim(), tokens[1].trim());
			}
			
			if(output.size() > 0) {
				return (T) output;
			}
		} catch(Exception e) { }
		
		return null;
	}
}
