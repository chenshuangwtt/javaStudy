package com.cs.test.week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadEchoServer {
	public static ExecutorService executorService = Executors.newCachedThreadPool();
	public static void main(String[] args) {
		ServerSocket echoSocket = null;
		Socket clientSocket = null;
		try {
			echoSocket = new ServerSocket(8000);
		} catch (IOException e) {
			e.printStackTrace();
		}
		while (true) {
			try {
				clientSocket = echoSocket.accept();
				System.out.println(clientSocket.getRemoteSocketAddress()
						+ " connect");
				executorService.execute(new HandleMsg(clientSocket));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	static class HandleMsg implements Runnable {
		Socket clientSocket;
		public HandleMsg(Socket clientSocket) {
			this.clientSocket = clientSocket;
		}
		public void run() {
			BufferedReader is = null;PrintWriter os = null;
			try {
				is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				os = new PrintWriter(clientSocket.getOutputStream(), true);
				// 从InputStream当中读取客户端所发送的数据
				String inputLine = null;
				long b = System.currentTimeMillis();
				while ((inputLine = is.readLine()) != null) {
					os.println(inputLine);
				}
				long e = System.currentTimeMillis();
				System.out.println("spend: " + (e - b) + "ms");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (is != null) {is.close();}
					if (os != null) {os.close();}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
