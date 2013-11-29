package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import exception.ServerConfigException;

/**
 * Contains methods that deals with Srvus configuration file.
 * This should be, if the not the first, among the first things to be loaded.
 * @author Matheus Salmi
 */
public class ServerConfig {
	private static final Properties data = new Properties();
	
	private ServerConfig() { }
	
	/**
	 * Load configuration from file
	 */
	public static void load() throws ServerConfigException {
		if( data.isEmpty()) {
			try {
				data.load(new FileInputStream(new File(PathUtil.SRVUS_CONFIG)));
			} catch (FileNotFoundException e) {
				throw new ServerConfigException("Configuration file could not be found.");
			} catch (IOException e) {
				throw new ServerConfigException("Configuration file could not be parsed.");
			}
		}
	}
	
	/**
	 * Returns a property
	 * @param name
	 * @return
	 */
	public static String get(String name) {
		return data.getProperty(name);
	}
}