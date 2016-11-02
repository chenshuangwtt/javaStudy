package com.cs.test.week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.LockSupport;

public class SocketClient {
	private static ExecutorService executorService = Executors.newCachedThreadPool();
	private static final int sleep_time = 1000 * 1000 * 1000;

	private static int getRandom(int count) {
	    return (int) Math.round(Math.random() * (count));
	}
	private static String string = "abcdefghijklmnopqrstuvwxyz";   
	private static String getRandomString(int length){
	    StringBuffer sb = new StringBuffer();
	    int len = string.length();
	    for (int i = 0; i < length; i++) {
	        sb.append(string.charAt(getRandom(len-1)));
	    }
	    return sb.toString();
	}
	public static class EchoClient implements Runnable {
		@Override
		public void run() {
			Socket client = null;
			PrintWriter writer = null;
			BufferedReader reader = null;
			try {
				client = new Socket();
				client.connect(new InetSocketAddress("localhost", 8000));
				writer = new PrintWriter(client.getOutputStream(), true);
				for(int j=0;j<10;j++){
					writer.print(getRandomString(1000));
					LockSupport.parkNanos(sleep_time);
				}
				writer.println();
				writer.flush();
				reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
				System.out.println("from server:" + reader.readLine());
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (writer != null) {writer.close();}
					if (reader != null) {reader.close();}
					if (client != null) {client.close();}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		EchoClient echoClient = new EchoClient();
		for (int i = 0; i < 5; i++) {
			executorService.execute(echoClient);
		}
	}
}
