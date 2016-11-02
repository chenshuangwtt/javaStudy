package com.cs.test.week10.reactor;

import java.net.ServerSocket;
import java.net.Socket;

public interface IoConfig {

	public void configServerSocket(ServerSocket socket);

	public void configSocket(Socket socket);
}