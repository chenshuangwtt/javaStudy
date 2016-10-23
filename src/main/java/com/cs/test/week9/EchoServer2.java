package com.cs.test.week9;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class EchoServer2 {
	public static void main(String[] args) throws IOException {
		Selector selector = Selector.open();
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.configureBlocking(false);
		InetSocketAddress address = new InetSocketAddress(9000);
		serverSocketChannel.socket().bind(address);
		System.out.println("started at " + address);
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		while (true) {
			int selectedNum = selector.select();
			System.out.println("selected Number is " + selectedNum);
			Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
			while (iterator.hasNext()) {
				SelectionKey selectionKey = iterator.next();
				if ((selectionKey.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) {
					ServerSocketChannel serverChannel = (ServerSocketChannel) selectionKey.channel();
					SocketChannel socketChannel = serverChannel.accept();
					socketChannel.configureBlocking(false);
					socketChannel.register(selector, SelectionKey.OP_READ);
					socketChannel.write(ByteBuffer.wrap("Welcome to basara.....\r\n".getBytes()));
				} else if ((selectionKey.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ) {
					System.out.println("received read event");
					SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
					ByteBuffer buffer = ByteBuffer.allocate(100);
					socketChannel.read(buffer);
					buffer = (ByteBuffer) selectionKey.attachment(); // 绑定一个用户对象
					if (buffer == null || buffer.hasRemaining()) {
						int datalength = socketChannel.socket().getSendBufferSize() * 50;
						buffer = ByteBuffer.allocate(datalength);
						for (int i = 0; i < buffer.capacity() - 2; i++) {
							buffer.put((byte) ('a' + i % 25));
						}
						buffer.flip();
						System.out.println("send another huge block data" + datalength);
					}
					int writed = socketChannel.write(buffer);
					System.out.println("writed:" + writed);
					if (buffer.hasRemaining()) {
						System.out.println("not write finished.remains" + buffer.remaining());
						buffer = buffer.compact();
						selectionKey.attach(buffer);
						selectionKey.interestOps(selectionKey.interestOps() | SelectionKey.OP_WRITE);
					} else {
						System.out.println("block write finished");
						selectionKey.attach(null);
						selectionKey.interestOps(selectionKey.interestOps() & ~SelectionKey.OP_WRITE);
					}
				} else if ((selectionKey.readyOps() & SelectionKey.OP_WRITE) == SelectionKey.OP_WRITE) {
					System.out.println("received write event");
					ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
					SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
					if (buffer != null) {
						int writed = socketChannel.write(buffer);
						System.out.println("writed:" + writed);
						if (buffer.hasRemaining()) {
							System.out.println("not write finished,bind to session,remains " + buffer.remaining());
							buffer = buffer.compact();
							selectionKey.attach(buffer);
							selectionKey.interestOps(selectionKey.interestOps() | SelectionKey.OP_WRITE);
						} else {
							System.out.println("block write finished");
							selectionKey.attach(null);
							selectionKey.interestOps(selectionKey.interestOps() & ~SelectionKey.OP_WRITE);
						}
					}
				}
				iterator.remove();
			}

		}
	}
}
