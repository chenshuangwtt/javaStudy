package com.cs.test.week4.collectors;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Collectors.averagingDouble calculates the average of stream element as double
 * data type. It returns a Collector instance. The collect method of stream can
 * accept the collector instance and will return the average value calculated by
 * Collectors.averagingDouble
 */
public class AveragingDoubleExample {
	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1, 2, 3, 4);
		//求平均值
		Double result = list.stream().
				collect(Collectors.averagingDouble(d -> d * 2));
		System.out.println(result);
	}
}
