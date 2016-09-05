package com.cs.test.week4.stream;

import java.util.Arrays;
import java.util.List;

/**
 * findAny method can find any element from stream. It returns Optional
 * instance. If there is no data in stream, it returns empty Optional instance
 */
public class FindAnyDemo {
	public static void main(String[] args) {
		List<String> list = Arrays.asList("AA", "BB", "CC");
		list.stream().findAny().ifPresent(s -> System.out.println(s));
	}
}
