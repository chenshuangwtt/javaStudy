package com.cs.test.week10.reactor;

public class MainServer {
	public static void main(String[] args) throws Exception {
		MyNIOReactor  reactor = new MyNIOReactor(9000);
		reactor.start();
	}
}	
