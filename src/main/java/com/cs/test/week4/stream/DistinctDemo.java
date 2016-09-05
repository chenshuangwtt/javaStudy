package com.cs.test.week4.stream;

import java.util.Arrays;
import java.util.List;

/**
 * distinct() method in stream API returns stream with distinct element.
 * Distinct element is decided by equal method of Object class
 */
public class DistinctDemo {
	public static void main(String[] args) {
		List<String> list = Arrays.asList("AA", "AA", "BB");
		long l = list.stream().distinct().count();
		System.out.println("Number of distinct element:" + l);
	}
}
