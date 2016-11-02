package com.cs.test.week10.reactor;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class MyNIOReactor extends Thread {
	final Selector selector;
	final ServerSocketChannel serverSocketChannel;

	public MyNIOReactor(int port) throws Exception {
		selector = Selector.open();
		serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.configureBlocking(false);
		InetSocketAddress address = new InetSocketAddress(port);
		serverSocketChannel.socket().bind(address);
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		System.out.println("started at " + address);
	}
	
	public void run(){
		while(true){
			Set<SelectionKey>  selectionKeys = null;
			try {
				selector.select();
				selectionKeys = selector.selectedKeys();
				Iterator iterator = selectionKeys.iterator();
				while (iterator.hasNext()) {
					SelectionKey selectionKey = (SelectionKey) iterator.next();
					if (selectionKey.isAcceptable()) {
						new MyAccepter().run();
					}else{
						((MyHandler)selectionKey.attachment()).run();
					}
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	class  MyAccepter{
		public void run(){
			try {
				SocketChannel socketChannel = serverSocketChannel.accept();
				new MyHandler(selector,socketChannel);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}





