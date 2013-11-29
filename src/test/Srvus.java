package test;

import java.net.Socket;
import java.nio.file.Path;
import java.util.Date;

import messages.Request;
import messages.Response;
import util.FileManager;
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
			
			Request req = new Request(so.getInputStream());
			System.out.print(req.getRequestLine()); // start line
			System.out.print(req.getRequestFields()); // header
			System.out.println("-----");
			System.out.println(req.getBody()); // message body
			System.out.println("-----");
			
			// process file request
			FileManager fman = new FileManager(req);
			Path rfile = fman.getFilePath();
			
			// send response
			Response out = new Response(so.getOutputStream());
			out.setStatusCode(EStatusCode.C_200);
			out.setBody(rfile);
			out.send();
			
			// close input and output
			so.close();
		} catch(HttpException e) {
			e.printStackTrace();
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
			out.setStatusCode(httpexc.getStatusCode());
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