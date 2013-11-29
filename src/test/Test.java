package test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import util.CloseableUtil;
import util.ServerConfig;

public class Test {

	public static void main(String[] args) throws IOException {
		ServerSocket ss = null;
		
		try {
			int port = Integer.valueOf(ServerConfig.get("server.port"));
			ss = new ServerSocket(port);

			System.out.println("Listening to port: " + port);

			while(true) {
				Socket so = ss.accept();
				
				Thread t = new Thread(new Srvus(so));
				t.start();
			}
		} catch(NumberFormatException e) {
			System.err.println("Invalid host port!");
		} finally {
			CloseableUtil.tryClose(ss);
		}
	}
}