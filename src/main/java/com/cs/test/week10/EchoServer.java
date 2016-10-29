package com.cs.test.week10;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	public static void main(String[] args) {
		ServerSocket  serverSocket = null;
		Socket clientSocket = null;
		try {
			serverSocket = new ServerSocket(8000);
		} catch (IOException e) {
			e.printStackTrace();
		}
		while (true) {
			try {
				clientSocket = serverSocket.accept();
				System.out.println(clientSocket.getRemoteSocketAddress()+"connect!");
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
}
