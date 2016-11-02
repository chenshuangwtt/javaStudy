package com.cs.test.week10.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Reactor implements Runnable {

	final Selector selector;
	final ServerSocketChannel serverSocketChannel;
	final boolean isWithThreadPool;

	public Reactor(int port, boolean isWithThreadPool) throws IOException {
		this.isWithThreadPool = isWithThreadPool;
		selector = Selector.open();
		serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.socket().bind(new InetSocketAddress(port));
		serverSocketChannel.configureBlocking(false);
		SelectionKey selectionKey0 = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		selectionKey0.attach(new Acceptor());
	}

	@Override
	public void run() {
		System.out.println("Server listening to port: " + serverSocketChannel.socket().getLocalPort());
		try {
			while (true) {
				selector.select();
				Set<SelectionKey> selectionKeys = selector.selectedKeys();
				Iterator<SelectionKey> iterator = selectionKeys.iterator();
				while (iterator.hasNext()) {
					dispatch(iterator.next());
					iterator.remove();
				}

			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	private void dispatch(SelectionKey next) {
		Runnable r = (Runnable) (next.attachment());
		if (r != null) {
			r.run();
		}
	}

	class Acceptor implements Runnable {
		public void run() {
			try {
				SocketChannel socketChannel = serverSocketChannel.accept();
				if (socketChannel != null) {
					if (isWithThreadPool)
						new HandlerWithThreadPool(selector, socketChannel);
					else
						new Handler(selector, socketChannel);
				}
				System.out.println("Connection Accepted by Reactor");
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

}
