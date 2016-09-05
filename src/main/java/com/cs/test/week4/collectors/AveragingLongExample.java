package com.cs.test.week4.collectors;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Collectors.averagingLong calculates the average of stream element as long
 * data type.
 */
public class AveragingLongExample {
	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1, 2, 3, 4);
		Double result = list.stream().collect(Collectors.averagingLong(v -> v * 2));
		System.out.println(result);
	}
}
