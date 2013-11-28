package util;

import java.nio.file.Path;
import java.nio.file.Paths;

import messages.Request;
import exception.HttpException;

/**
 * Manages the files requested on the request message.
 * @author Matheus Salmi
 */
public class FileManager {
	private Request request = null;
	
	public FileManager(Request request) {
		if(request == null) {
			throw new IllegalArgumentException("Request cannot be null");
		}
		
		this.request = request;
	}
	
	/**
	 * Returns file path
	 * @return
	 * @throws HttpException
	 */
	public Path getFilePath() {
		String path = PathUtil.SITES_DIR + request.getRequestLine().getRequestUri();
		return Paths.get(path);
	}
}
