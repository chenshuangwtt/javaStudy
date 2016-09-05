package com.cs.test.week4.collectors;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CollectingAndThenExample {
	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1, 2, 3, 4);
		Double result = list.stream()
				.collect(Collectors.collectingAndThen(
						Collectors.averagingLong(v -> v * 2), s -> s * s));
		System.out.println(result);
	}
}
