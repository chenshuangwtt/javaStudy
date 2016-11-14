package com.cs.test.week12;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainServer {
	public static void main(String[] args) throws IOException {
		ExecutorService executorService = Executors.newCachedThreadPool();
		MyNIORector[] reactors = new MyNIORector[Runtime.getRuntime().availableProcessors()];
		for(int i =0;i<reactors.length;i++){
			reactors[i] = new MyNIORector(executorService);
			reactors[i].start();
		}
		NIOAcceptor acceptor = new NIOAcceptor(9000, reactors);
		acceptor.start();
	}
}
