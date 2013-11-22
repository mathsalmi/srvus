package test;

import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

import messages.Request;

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
			req.process();
			System.out.println(req.getRequestLine()); // start line
			System.out.println(req.getRequestFields()); // header
			System.out.println("-----");
			System.out.println(req.getBody()); // message body
			System.out.println("-----");
			
			// send response
			PrintWriter out = new PrintWriter(so.getOutputStream());
			out.write("HTTP/1.1 200 OK\r\n");
	        out.write("Content-Type: text/html\r\n\r\n");
	        out.write(Content.get());
			out.flush();
			
			// close input and output
			out.close();
			so.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}