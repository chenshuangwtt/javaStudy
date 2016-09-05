package com.cs.test.week4.stream;

import java.util.Arrays;
import java.util.List;

/**
 * min method returns minimum element on the basis of given comparator. It
 * returns Optional instance
 * 
 */
public class MinDemo {
	public static void main(String[] args) {
		List<String> list = Arrays.asList("CC", "DD", "AA", "BB");
		list.stream().min(new StringComparator())
			.ifPresent(s -> System.out.println(s));
	}
}
