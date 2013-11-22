package entities;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Represents a request/response header field
 * @author Matheus Salmi
 */
public abstract class HeaderFields extends HashMap<String, String> {
	private static final long serialVersionUID = 4654245165146963690L;
	
	/**
	 * Tries to get an element given its key.
	 * The key is case-insensitive (see @url http://www.w3.org/Protocols/rfc2616/rfc2616-sec4.html#sec4.2);
	 * @param key
	 * @return
	 */
	public String get(String key) {
		Set<String> keys = keySet();
		if(keys != null) {
			for(String item : keys) {
				if(item.equalsIgnoreCase(key)) {
					return super.get(item);
				}
			}
		}
		
		return null;
    }
	
	@Override
	public String toString() {
		StringBuilder out = new StringBuilder();
		Set<Map.Entry<String, String>> values = entrySet();
		if(values != null) {
			for(Map.Entry<String, String> value : values) {
				out.append(value.getKey());
				out.append(" : ");
				out.append(value.getValue());
				out.append("\n");
			}
		}
		
		return out.toString();
	}
}