package com.cs.test.week4.stream;

import java.util.stream.Stream;

/**
 * Stream.generate returns infinite sequential and unordered stream. We need to
 * pass supplier to generate element.
 */
public class GenerateDemo {
	public static void main(String[] args) {
		Item item = new Item("AA");
		Stream<String> stream = Stream.generate(item::getName);
		stream.forEach(s -> System.out.println(s));
	}
}
