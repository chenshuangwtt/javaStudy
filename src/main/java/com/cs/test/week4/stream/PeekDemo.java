package com.cs.test.week4.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Stream.peek peek returns stream itself after 
 * applying the action passed as consumer object
 */
public class PeekDemo {
	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(10, 11, 12);
		list.stream().peek(i -> System.out.println(i * i))
			.collect(Collectors.toList());
	}
}
