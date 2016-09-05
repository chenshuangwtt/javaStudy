package com.cs.test.week4.stream;

import java.util.Arrays;
import java.util.List;

/**
 * findFirst method returns first element of the stream as an Optional instance.
 * If stream is empty, it returns empty Optional instance
 */
public class FindFirstDemo {
	public static void main(String[] args) {
		List<String> list = Arrays.asList("XX", "YY", "ZZ");
		list.stream().findFirst().ifPresent(s -> System.out.println(s));
	}
}


