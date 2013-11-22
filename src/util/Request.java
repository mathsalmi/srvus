package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import entities.RequestFields;
import entities.RequestLine;
import enums.EMethod;
import enums.EStatusCode;
import exception.HttpException;

/**
 * Represents an HTTP Request
 * @author Matheus Salmi
 */
public class Request {
	private InputStream inputstream = null;
	private RequestLine requestLine = null;
	private RequestFields requestFields = null;
	private String body = "";
	
	private Request() {
		this.requestFields = new RequestFields();
	}
	
	public Request(InputStream inputstream) {
		this.inputstream = inputstream;
	}
	
	/**
	 * Processes the request
	 * @throws IOException 
	 */
	public void process() throws HttpException, IOException {
		this.processHeader();
		this.processBody();
	}
	
	
	private void processHeader() throws HttpException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(inputstream));
		
		String line;
		while((line = in.readLine()) != null && ! line.isEmpty()) {
			if(line.indexOf(':') == -1) {
				// request-line
				this.requestLine = this.parseRequestLine(line);
			} else {
				// header field
				String[] tokens = line.split(":");
				if(tokens.length == 2) {
					this.requestFields.put(tokens[0].trim(), tokens[1].trim());
				}
			}
	    }
	}
	
	private void processBody() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(inputstream));
		
		try {
			// parses message body
			int contentLength = 0;
			String objContentLength = this.requestFields.get("Content-Length");
			if(objContentLength != null) {
				contentLength = Integer.parseInt(objContentLength);
			}
			
			StringBuilder body = new StringBuilder();
			if(contentLength > 0) {
	            int read;
	            while((read = in.read()) != -1) {
	                body.append((char) read);
	                if (body.length() == contentLength)
	                    break;
	            }
	        }
			
			this.body = body.toString();
		} catch(NumberFormatException e) { } // TODO: write an IntegerUtil.tryparse
	}
	
	private RequestLine parseRequestLine(String line) throws HttpException {
		if(line != null && ! line.isEmpty()) {
			String[] tokens = line.split(" ");
			
			// check tokens
			if(tokens.length <= 0) {
				throw new HttpException(EStatusCode.C_400);
			}
			
			// check method
			EMethod method = EMethod.find(tokens[0]);
			if(method == null) {
				throw new HttpException(EStatusCode.C_501);
			}
			
			// check HTTP version
			String httpVersion = tokens[2];
			if( ! HttpUtil.isSupported(httpVersion)) {
				throw new HttpException(EStatusCode.C_505);
			}
			
			RequestLine output = new RequestLine();
			output.setMethod(method);
			output.setRequestUri(tokens[1]); // TODO: validate request uri
			output.setHttpVersion(httpVersion);
			
			return output;
		}
		
		return null;
	}
}
