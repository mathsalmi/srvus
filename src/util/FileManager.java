package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import enums.EStatusCode;
import exception.HttpException;
import messages.Request;

/**
 * Manages the files requested on the request message.
 * @author Matheus Salmi
 */
public class FileManager {
	private Request request = null;
	private static final String CURR_DIR = System.getProperty("user.dir");
	
	public FileManager(Request request) {
		if(request == null) {
			throw new IllegalArgumentException("Request cannot be null");
		}
		
		this.request = request;
	}
	
	public String getFileContent() throws HttpException {
		try {
			String path = FileManager.CURR_DIR + "/sites" + request.getRequestLine().getRequestUri();
			byte[] bytes = Files.readAllBytes(Paths.get(path));
			
			return new String(bytes);
		} catch (IOException | SecurityException e) {
			throw new HttpException(EStatusCode.C_404);
		} catch(OutOfMemoryError e) {
			throw new HttpException(EStatusCode.C_413);
		} catch(Exception e) {
			throw new HttpException(EStatusCode.C_500);
		}
	}
}
