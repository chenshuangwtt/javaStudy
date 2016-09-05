package com.cs.test.week4.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * concat() is a static method in stream API. It concats two stream object and
 * returns resulting stream object. In our example we have two lists which are
 * converted into stream and then concatenated
 * 
 * @author Administrator
 *
 */
public class ConcatDemo {
	public static void main(String[] args) {
		List<String> list1 = Arrays.asList("A1", "A2", "A3");
		List<String> list2 = Arrays.asList("B1", "B2", "B3");
		Stream<String> resStream = Stream.concat(list1.stream(), list2.stream());
		resStream.forEach(s -> System.out.println(s));
	}
}
