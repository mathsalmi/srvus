package entities;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Represents a request/response header field
 * @author Matheus Salmi
 */
public abstract class HeaderFields {
	private final HashMap<String, String> data = new HashMap<>();
	
	/**
	 * Gets an element given its key
	 * @param key
	 * @return
	 */
	public String get(String key) {
		return data.get(fixKey(key));
    }
	
	/**
	 * Returns all keys
	 * @return
	 */
	public Set<String> getKeys() {
		return data.keySet();
	}
	
	/**
	 * Returns all values
	 * @return
	 */
	public Collection<String> getValues() {
		return data.values();
	}
	
	/**
	 * Inserts a new header
	 * @param key
	 * @param value
	 */
	public void put(String key, String value) {
		data.put(fixKey(key), value);
	}
	
	/**
	 * Removes an element given its key
	 * @param key
	 * @return
	 */
	protected String remove(String key) {
		return data.remove(fixKey(key));
	}
	
	/**
	 * Removes all elements
	 */
	protected void clear() {
		data.clear();
	}
	
	/**
	 * Returns true if it contains no fields
	 * @return
	 */
	public boolean isEmpty() {
		return data.isEmpty();
	}
	
	/**
	 * Returns the number of fields added
	 * @return
	 */
	public int size() {
		return data.size();
	}
	
	/**
	 * Converts to a map structure
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> toMap() {
		return (Map<String, String>) data.clone();
	}
	
	/**
	 * Returns the key formatted
	 * @param key
	 * @return
	 */
	private String fixKey(String key) {
		if(key == null) {
			throw new IllegalArgumentException("Key cannot be null");
		}
		return key.toLowerCase();
	}
	
	/**
	 * Shows all header fields on the format specified by RFC 2616
	 * (@url http://www.w3.org/Protocols/rfc2616/rfc2616-sec4.html#sec4.2)
	 */
	@Override
	public String toString() {
		StringBuilder out = new StringBuilder();
		Set<Map.Entry<String, String>> values = data.entrySet();
		if(values != null) {
			for(Map.Entry<String, String> value : values) {
				out.append(value.getKey());
				out.append(" : ");
				out.append(value.getValue());
				out.append("\r\n");
			}
		}
		
		return out.toString();
	}
}