package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

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
			
			BufferedReader in = new BufferedReader(new InputStreamReader(so.getInputStream()));
			String inputLine;
			// it should test whether there is the "Content-length" field. 
			// If there is, then there is a message body, so we must read until its end;
			// If not, we can simply stop at an empty line. 
			// TODO: think how to return 400 if there is a message body when content-length wasn't sent. IDEA: perhaps a timeout?
			// check http://www.w3.org/Protocols/rfc2616/rfc2616-sec4.html#sec4.4
			while((inputLine = in.readLine()) != null && ! inputLine.isEmpty()) {
				System.out.println(inputLine);
		    }
			
			// send response
			PrintWriter out = new PrintWriter(so.getOutputStream());
			out.write("HTTP/1.1 200 OK\r\n");
	        out.write("Content-Type: text/html\r\n\r\n");
	        out.write("<!DOCTYPE html><html><body><h1>Hello world<form method=\"post\"><input type=\"text\" name=\"hello\"><input type=\"submit\"></form></h1></body></html>");
			out.flush();
			
			// close input and output
			in.close();
			out.close();
			so.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}