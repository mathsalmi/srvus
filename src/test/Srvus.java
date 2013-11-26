package test;

import java.io.InputStream;
import java.net.Socket;
import java.util.Date;

import messages.Request;
import messages.Response;
import enums.EStatusCode;
import exception.HttpException;

public class Srvus implements Runnable {
	Socket so = null;
	
	public Srvus(Socket so) {
		this.so = so;
	}

	@Override
	public void run() {
		
		try {
			// logs request header
			System.out.println("-----");
			System.out.println(new Date());
			System.out.println("-----");
			
			InputStream inputstream = so.getInputStream();
			Request req = new Request(inputstream);
			System.out.println(req.getRequestLine()); // start line
			System.out.println(req.getRequestFields()); // header
			System.out.println("-----");
			System.out.println(req.getBody()); // message body
			System.out.println("-----");
			
			
			// send response
			Response out = new Response(so.getOutputStream());
			out.setStatusLine(EStatusCode.C_200);
			out.addHeaderField("Content-type", "text/html");
			out.setBody(Content.get());
			out.send();
			
			// close input and output
			so.close();
		} catch(HttpException e) {
			this.respondWithError(e);
		} catch(Exception e) {
			e.printStackTrace();
			this.respondWithError(new HttpException(EStatusCode.C_500));
		}
	}
	
	// TODO: delete it
	private void respondWithError(HttpException httpexc) {
		try {
			Response out = new Response(so.getOutputStream());
			out.setStatusLine(httpexc.getStatusCode());
			out.addHeaderField("Content-type", "text/html");
			out.setBody("<h1>" + httpexc.toString() + "</h1>");
			out.send();
			
			// close
			so.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}