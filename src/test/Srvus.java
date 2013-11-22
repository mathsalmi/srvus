package test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
			BufferedReader in = new BufferedReader(new InputStreamReader(inputstream));
			
			// it should test whether there is the "Content-length" field. 
			// If there is, then there is a message body, so we must read until its end;
			// If not, we can simply stop at an empty line. 
			// TODO: think how to return 400 if there is a message body when content-length wasn't sent. IDEA: perhaps a timeout?
			// check http://www.w3.org/Protocols/rfc2616/rfc2616-sec4.html#sec4.4
			List<String> header = new ArrayList<>();
			String line;
			int length = 0;
			while((line = in.readLine()) != null) {
				if(line.isEmpty()) {
					break;
				}
				
				if(line.startsWith("Content-Length: ")) {
					int index = line.indexOf(':') + 1;
                    String len = line.substring(index).trim();
                    length = Integer.parseInt(len);
				}
				
				header.add(line);
		    }
			
			StringBuilder body = new StringBuilder();
			if (length > 0) {
                int read;
                while ((read = in.read()) != -1) {
                    body.append((char) read);
                    if (body.length() == length)
                        break;
                }
            }
			
			System.out.println("header");
			for(String field : header) {
				System.out.println(field);
			}
			
			System.out.println("body");
			System.out.println(body.toString());
			System.out.println("-----");
			
			// send response
			PrintWriter out = new PrintWriter(so.getOutputStream());
			out.write("HTTP/1.1 200 OK\r\n");
	        out.write("Content-Type: text/html\r\n\r\n");
	        out.write(Content.get());
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