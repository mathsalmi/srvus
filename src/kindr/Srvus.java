package kindr;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class Srvus implements Runnable {
	Socket so = null;
	
	public Srvus(Socket so) {
		this.so = so;
	}

	@Override
	public void run() {
		Scanner sr = null;
		
		try {
			// logs request header
			System.out.println("-----");
			System.out.println(new Date());
			System.out.println("-----");
			
			sr = new Scanner(so.getInputStream());
			while(sr.hasNextLine()) {
				System.out.println(sr.nextLine());
			}
			
			// send response
			PrintWriter out = new PrintWriter(so.getOutputStream(), true);
			out.write("HTTP/1.1 200 OK\r\n");
	        out.write("Content-Type: text/html\r\n\r\n");
	        out.write("<!DOCTYPE html><html><body><h1>Hello world</h1></body></html>");
			out.flush();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			sr.close();
		}
	}
}