package com.cs.test.week4.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Stream.flatMap returns the stream object. 
 * We need to pass a function as an argument. 
 * Function will be applied to each element of stream
 */
public class FlatMapDemo {
	public static void main(String[] args) {
		List<String> list1 = Arrays.asList("AAA", "BBB");
		List<String> list2 = Arrays.asList("CCC", "DDD");
		Stream.of(list1, list2).flatMap(list -> list.stream())
			.forEach(s -> System.out.println(s));
	}

}
