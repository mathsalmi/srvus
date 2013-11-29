package util;

import java.nio.file.Path;
import java.nio.file.Paths;

import messages.Request;
import entities.RequestLine;
import enums.EStatusCode;
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
	public Path getFilePath() throws HttpException {
		RequestLine rqLine = request.getRequestLine();
		if(rqLine == null) {
			throw new HttpException(EStatusCode.C_500);
		}
		
		String path = PathUtil.SITES_DIR + rqLine.getRequestUri();
		return Paths.get(path);
	}
}
