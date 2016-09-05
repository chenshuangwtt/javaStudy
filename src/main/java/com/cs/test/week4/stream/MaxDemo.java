package com.cs.test.week4.stream;

import java.util.Arrays;
import java.util.List;

/**
 * max method returns maximum element on the basis of given comparator. It
 * returns Optional instance
 *
 */
public class MaxDemo {
	public static void main(String[] args) {
		List<String> list = Arrays.asList("AA", "DD", "CC", "BB");
		list.stream().max(new StringComparator())
			.ifPresent(s -> System.out.println(s));
	}
}
