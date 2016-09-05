package com.cs.test.week4.stream;

import java.util.stream.Stream;

public class StreamTest {
	public static void main(String[] args) {
		Stream.of("d2", "a2", "b1", "b3", "c").filter(s -> {
	        System.out.println("filter: " + s);
	        return false;
	    }).forEach(s -> System.out.println("forEach: " + s));
	}
}
