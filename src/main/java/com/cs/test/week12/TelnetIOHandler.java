package com.cs.test.week12;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class TelnetIOHandler extends IOHandler {

	public TelnetIOHandler(Selector selector, SocketChannel socketChannel) throws IOException {
		super(selector, socketChannel);
	}

	private int lastMessagePos;

	@Override
	public void onConnected() throws IOException {
		System.out.println("connected  from " + this.socketChannel.getRemoteAddress());
		this.writeData("Welcome basara ...\r\nTelnet>".getBytes());
	}

	@Override
	public void doHandler() throws IOException {
		socketChannel.read(readBuffer);
		int readEndPos = readBuffer.position();
		String readedLine = null;
		for (int i = lastMessagePos; i < readEndPos; i++) {
			// System.out.println(readBuffer.get(i));
			if (readBuffer.get(i) == 13) { // a line finished
				byte[] lineBytes = new byte[i - lastMessagePos];
				readBuffer.position(lastMessagePos);
				readBuffer.get(lineBytes);
				lastMessagePos = i;
				readedLine = new String(lineBytes);
				System.out.println("received line ,lenth:" + readedLine.length() + " value " + readedLine);
				break;
			}
		}

		if (readedLine != null) {
			// 取消读事件关注，因为要应答数据
			selectionKey.interestOps(selectionKey.interestOps() & ~SelectionKey.OP_READ);
			// 处理指令
			processCommand(readedLine);
		}
		if (readBuffer.position() > readBuffer.capacity() / 2) {// 清理前面读过的废弃空间
			System.out.println(" rewind read byte buffer ,get more space  " + readBuffer.position());
			readBuffer.limit(readBuffer.position());
			readBuffer.position(lastMessagePos);
			readBuffer.compact();
			lastMessagePos = 0;
		}
	}

	private void processCommand(String readedLine) throws IOException {
		byte[] data=null;
		if (readedLine.startsWith("dir")) {
			readedLine = "cmd  /c " + readedLine;
			data = (LocalCmandUtil.callCmdAndgetResult(readedLine)+"\r\nTelnet>").getBytes("GBK");
			this.writeData(data);
		} else {
			data=new byte[1024*10];
			ByteBuffer tempBuf=ByteBuffer.wrap(data);
			for (int i = 0; i < tempBuf.capacity() - 10; i++) {
				tempBuf.put((byte) ('a' + i % 25));
			}
			tempBuf.put("\r\nTelnet>".getBytes());
		}
		this.writeData(data);
	}
}