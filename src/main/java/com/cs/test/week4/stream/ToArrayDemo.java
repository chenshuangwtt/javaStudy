package com.cs.test.week4.stream;

import java.util.stream.Stream;

/**
 * Stream.toArray Stream.toArray method converts a stream into array
 */
public class ToArrayDemo {
	public static void main(String[] args) {
		Object[] ob = Stream.of("A", "B", "C", "D").toArray();
		for (Object o : ob) {
			System.out.println(o.toString());
		}
	}
}
