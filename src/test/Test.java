package test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Test {

	public static void main(String[] args) throws IOException {
		ServerSocket ss = null;
		
		try {
			ss = new ServerSocket(8885);

			while(true) {
				Socket so = ss.accept();
				
				Thread t = new Thread(new Srvus(so));
				t.start();
			}
		} finally {
			ss.close();
		}
	}
}