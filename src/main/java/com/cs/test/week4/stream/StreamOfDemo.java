package com.cs.test.week4.stream;

import java.util.stream.Stream;

/**
 * Stream.of is a method which returns sequential ordered stream. We can pass
 * any object comma separated and will get stream object
 * 
 */
public class StreamOfDemo {
	public static void main(String[] args) {
		Stream.of("Ram", "Shyam", "Mohan").forEach(s -> System.out.println(s));
	}
}
