package com.cs.test.week10.reactor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

import com.sun.org.apache.bcel.internal.generic.Select;

public class MyHandler implements Runnable {
	private  final SelectionKey  selectionKey;
	private  final SocketChannel  socketChannel;
	private ByteBuffer  writeBuffer;
	private ByteBuffer  readBuffer;
	private int lastReadPos;
	
	public MyHandler(final Selector selector,SocketChannel socketChannel) throws IOException {
		socketChannel.configureBlocking(false);
		this.socketChannel= socketChannel;
		selectionKey = socketChannel.register(selector, 0);
		selectionKey.interestOps(SelectionKey.OP_READ);
		writeBuffer = ByteBuffer.allocateDirect(1024*2);
		readBuffer = ByteBuffer.allocateDirect(1024);
		//绑定会话
		selectionKey.attach(this);
		writeBuffer.put("Welcome to basara.....\r\n".getBytes());
		writeBuffer.flip();
		doWriteData();
	}
	
	
	@Override
	public void run() {
		try{
			if (selectionKey.isReadable()) {
				doReadData();
			}else if(selectionKey.isWritable()){
				doWriteData();
			}
		}catch(Exception e){
			e.printStackTrace();
			selectionKey.cancel();
			try {
				socketChannel.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	private void doWriteData() throws IOException {
		writeToChannel();
	}

	private void doReadData() throws IOException {
		socketChannel.read(readBuffer);
		int readEndPos = readBuffer.position();
		String readedLine = null;
		for(int i = lastReadPos;i<readEndPos;i++){
			if (readBuffer.get(i)==13) {
				byte[] lineBytes = new byte[i-lastReadPos];
				readBuffer.position(lastReadPos);
				readBuffer.get(lineBytes);
				lastReadPos = i;
				readedLine = new  String(lineBytes);
				System.out.println("received line,length:"+readedLine.length()+" value"+readedLine);
				break;
			}
		}
		if (readedLine!=null) {
			//取消读事件关注，
			selectionKey.interestOps(selectionKey.interestOps()& ~ SelectionKey.OP_READ);
			//处理指令
			processCommand(readedLine);
		}
		if (readBuffer.position() > readBuffer.capacity() /2) {
			//清理已经废弃的空间
			System.out.println("rewind read byte buffer,get more space "+readBuffer.position());
			readBuffer.limit(readBuffer.position());
			readBuffer.position(lastReadPos);
			readBuffer.compact();
			lastReadPos = 0;
		}
	}

	private void processCommand(String readedLine) throws IOException {
		if (readedLine.startsWith("dir")) {
			readedLine ="cmd /c "+readedLine;
			String result = callCmdAndgetResult(readedLine);
			writeBuffer.put(result.getBytes("GBK"));
			writeBuffer.put("\r\n".getBytes());
		}else{
			for(int  i=0;i<writeBuffer.capacity()-10;i++){
				writeBuffer.put((byte) ('a' + i % 25));
			}
			writeBuffer.put("\r\n".getBytes());
		}
		writeBuffer.flip();
		writeToChannel();
	}

	private void writeToChannel() throws IOException {
		int writed = socketChannel.write(writeBuffer);
		System.out.println("writed:"+writed);
		if (writeBuffer.hasRemaining()) {
			System.out.println("writed "+writed+" not write finished so bind to session,remains "+writeBuffer.remaining());
			selectionKey.interestOps(selectionKey.interestOps()| SelectionKey.OP_WRITE);
		}else{
			System.out.println("block write finished");
			writeBuffer.clear();
			selectionKey.interestOps(selectionKey.interestOps() & ~ SelectionKey.OP_WRITE | SelectionKey.OP_READ);
		}
	}

	private String callCmdAndgetResult(String cmd) {
		StringBuilder  result = new  StringBuilder();
		try {
			ProcessBuilder  processBuilder = new ProcessBuilder(cmd.split("\\s"));//创建进程管理实例
			Process process = processBuilder.start();	//启动进程
			InputStream  inputStream = process.getInputStream();
			InputStreamReader  inputStreamReader = new  InputStreamReader(inputStream,"GBK");
			BufferedReader  bufferedReader = new  BufferedReader(inputStreamReader);
			String line;
			while((line=bufferedReader.readLine())!=null){	//循环读取数据
				result.append(line);
			}
			inputStream.close();
			inputStreamReader.close();
			bufferedReader.close();
			process.waitFor();
		} catch (Exception e) {	//捕获异常
				result.append(e.toString());
		}
		return result.toString();
	}
}
