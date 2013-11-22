package util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import entities.RequestFields;
import entities.RequestLine;
import enums.EMethod;
import enums.EStatusCode;
import exception.HttpException;

/**
 * Represents an request
 * @author Matheus Salmi
 * @deprecated
 */
public class RequestOld {
	
	/**
	 * Parses the request line from the input stream.
	 * @param inputstream
	 * @return
	 */
	public static RequestLine parseRequestLine(InputStream inputstream) throws HttpException {
		try {
			BufferedReader buffer = new BufferedReader(new InputStreamReader(inputstream));
			String line = buffer.readLine();
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
		} catch(HttpException e) { 
			throw e;
		} catch(Exception e) { }
		
		return null;
	}
	
	/**
	 * Parses headers from the input stream
	 * @param inputstream
	 * @return
	 */
	public static RequestFields parseHeader(InputStream inputstream) {
		return HeaderUtil.parse(inputstream, RequestFields.class);
	}
}