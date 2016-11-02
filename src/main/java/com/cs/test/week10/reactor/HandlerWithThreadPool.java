package com.cs.test.week10.reactor;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HandlerWithThreadPool extends Handler {

	static ExecutorService pool = Executors.newFixedThreadPool(10);
	static final int PROCESSING = 2;

	HandlerWithThreadPool(Selector selector, SocketChannel channel) throws IOException {
		super(selector, channel);
	}

	void  read() throws IOException{
		int  readCount = socketChannel.read(input);
		if (readCount > 0) {
			state = PROCESSING;
			pool.execute(new Processer(readCount));
		}
		selectionKey.interestOps(SelectionKey.OP_WRITE);
	}
	
	
	class Processer implements Runnable {
        int readCount;
        Processer(int readCount) {
            this.readCount =  readCount;
        }
        public void run() {
            processAndHandOff(readCount);
        }
    }
	
	 //Start processing in a new Processer Thread and Hand off to the reactor thread.
    synchronized void processAndHandOff(int readCount) {
        readProcess(readCount);
        //Read processing done. Now the server is ready to send a message to the client.
        state = SENDING;
    }
	
}
