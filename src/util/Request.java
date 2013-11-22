package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
	private String body = null;
	
	private Request() {
		this.requestFields = new RequestFields();
	}
	
	public Request(InputStream inputstream) {
		this();
		this.inputstream = inputstream;
	}
	
	/**
	 * Processes the request
	 * @throws IOException 
	 */
	public void process() throws HttpException {
		BufferedReader in = new BufferedReader(new InputStreamReader(inputstream));
		try {
			this.processHeader(in);
			this.processBody(in);
		} catch (IOException e) {
		}
	}
	
	/**
	 * Processes the input stream in order to get the start-line and the header fields
	 * @param in the input stream converted into BufferedReader
	 * @throws HttpException
	 * @throws IOException
	 */
	private void processHeader(BufferedReader in) throws HttpException, IOException {
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
	
	/**
	 * Processes the input stream in order to get the message body
	 * @param in the input stream converted into BufferedReader
	 * @throws HttpException
	 * @throws IOException
	 */
	private void processBody(BufferedReader in) throws IOException {
		int contentLength = IntegerUtil.tryParse(this.requestFields.get("Content-Length"));
		
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
	}
	
	/**
	 * Parses an line according to the request-line rules.
	 * @param line the line to be parsed
	 * @return
	 * @throws HttpException
	 */
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

	/**
	 * @return the requestLine
	 */
	public RequestLine getRequestLine() {
		return requestLine;
	}

	/**
	 * @return the requestFields
	 */
	public RequestFields getRequestFields() {
		return requestFields;
	}

	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}
}