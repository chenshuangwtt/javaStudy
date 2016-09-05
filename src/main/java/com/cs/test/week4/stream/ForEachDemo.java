package com.cs.test.week4.stream;

import java.util.stream.Stream;

/**
 * Stream.forEach and Stream.forEachOrdered forEach method accepts consumer as
 * an argument and that consumer is applied to each element of the stream. We
 * can create a consumer to print the stream element and use it with forEach
 * method. forEachOrdered method does the same thing but in the encounter order
 * of the stream
 *
 */
public class ForEachDemo {
	public static void main(String[] args) {
		System.out.println("forEach Demo");
		Stream.of("AAA", "BBB", "CCC").
			forEach(s -> System.out.println("Output:" + s));
		System.out.println("forEachOrdered Demo");
		Stream.of("AAA", "BBB", "CCC").
			forEachOrdered(s -> System.out.println("Output:" + s));
	}
}
