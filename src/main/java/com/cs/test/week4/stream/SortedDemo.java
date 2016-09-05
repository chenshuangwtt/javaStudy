package com.cs.test.week4.stream;

import java.util.Arrays;
import java.util.List;

/**
 * sorted() method in stream API sorts the elements of stream on the basis of
 * natural order. We can also pass our comparator object to get custom sorting
 * 
 */
public class SortedDemo {
	public static void main(String[] args) {
		List<String> list = Arrays.asList("DC", "CD", "AD");
		list.stream().sorted().forEach(s -> System.out.println(s));
	}
}
