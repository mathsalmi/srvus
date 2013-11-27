package util;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Contains methods for dealing with MIME types.
 * It is useful because Files.probeContentType is buggy on OSX.
 * 
 * PS: this class uses a file with MIME types made for httpd.
 * @author Matheus Salmi
 */
public class MIMEUtil {
	private static final Map<String, String> data = new HashMap<>();
	private static final Pattern pattern = Pattern.compile("(\\p{Graph}+)\\p{Blank}+([\\p{Graph}\\s]+)");

	private MIMEUtil() { }
	
	/**
	 * Loads all lines from the MIME type file
	 * @return
	 */
	private static List<String> loadFile() {
		List<String> out = null;
		try {
			out = Files.readAllLines(Paths.get(DirUtil.ETC_DIR + "/mime.types"), StandardCharsets.UTF_8);
		} catch(Exception e) { }
		
		return out;
	}
	
	/**
	 * Parses the MIME type file
	 */
	private static void parseFile() {
		if( ! data.isEmpty()) {
			return;
		}
		
		List<String> lines = loadFile();
		if(lines != null) {
			for(String line : lines) {
				// comments
				if(line.startsWith("#")) {
					continue;
				}
				
				// find extension and type
				Matcher m = pattern.matcher(line);
				m.matches();
				String key = m.group(2);
				String value = m.group(1);
				
				// add to map
				if(key.contains(" ")) {
					// there may be more than one extension in one line
					String[] keys = key.split(" ");
					for(String item : keys) {
						addToMap(item, value);
					}
				} else {
					addToMap(key, value);
				}
			}
		}
	}
	
	/**
	 * Adds values to the data map
	 * @param key
	 * @param value
	 */
	private static void addToMap(String key, String value) {
		if(key != null && value != null)
			data.put(key, value);
	}
	
	/**
	 * Returns file MIME type given its extension
	 * @param ext
	 * @return
	 */
	public static String findType(String ext) {
		parseFile();
		return data.get(ext);
	}
	
	/**
	 * Returns file MIME type given its path
	 * @param path
	 * @return
	 */
	public static String findType(Path path) {
		return findType(FileUtil.getExt(path));
	}
}