package com.cs.test.week12.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ThreadLocalRandom;

public class NIOAcceptor extends Thread {
	private final ServerSocketChannel  serverSocketChannel;
	private final MyNIORector[] reactors;
	
	public NIOAcceptor(int bindPort,MyNIORector[] reactors) throws IOException{
		this.reactors = reactors;
		serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.configureBlocking(false);
		InetSocketAddress   address = new InetSocketAddress(bindPort);
		serverSocketChannel.socket().bind(address);
		System.out.println("started at " + address);
	}
	
	public  void run(){
		while(true){
			try {
				SocketChannel socketChannel = serverSocketChannel.accept();
				System.out.println("Connection Accepted "+socketChannel.getRemoteAddress());
				int  nextReactor = ThreadLocalRandom.current().nextInt(0,reactors.length);
				reactors[nextReactor].registerNewClient(socketChannel);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
