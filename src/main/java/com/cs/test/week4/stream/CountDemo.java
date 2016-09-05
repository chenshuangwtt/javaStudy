package com.cs.test.week4.stream;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * count() method in stream API just counts the elements in stream object. It
 * returns long value. In our example we have a list and we convert it into
 * stream then apply count() method on it
 */
public class CountDemo {
	public static void main(String[] args) {
		List<String> list = Arrays.asList("AA", "AB", "CC");
		Predicate<String> predicate = s -> s.startsWith("A");
		long l = list.stream().filter(predicate).count();
		System.out.println("Number of Matching Element:" + l);
	}
}
