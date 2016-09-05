package com.cs.test.week4.stream;

import java.util.Arrays;
import java.util.List;

/**
 * limit method of stream API, returns stream instance with the given number of
 * element in limit as an argument. limit method selects the elements from start
 * 
 */
public class LimitDemo {
	public static void main(String[] args) {
		List<String> list = Arrays.asList("AA", "BB", "CC", "DD", "EE");
		list.stream().limit(3).forEach(s -> System.out.println(s));
	}
}
