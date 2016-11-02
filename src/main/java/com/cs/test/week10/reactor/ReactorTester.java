package com.cs.test.week10.reactor;

import java.io.IOException;

public class ReactorTester {
	public static void main(String[] args) throws IOException{
	    Reactor reactor  = new Reactor(9900, false);
	    new Thread(reactor).start();
	}
}