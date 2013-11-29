package test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import util.CloseableUtil;
import util.IntegerUtil;
import util.ServerConfig;

public class Test {

	public static void main(String[] args) throws IOException {
		ServerSocket ss = null;
		
		try {
			ServerConfig.load();
			ss = new ServerSocket(IntegerUtil.tryParse(ServerConfig.get("server.port")));

			while(true) {
				Socket so = ss.accept();
				
				Thread t = new Thread(new Srvus(so));
				t.start();
			}
		} finally {
			CloseableUtil.tryClose(ss);
		}
	}
}