package com.cs.test.week4.collectors;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//Collectors.averagingInt calculates average of stream element as integer data type. 
public class AveragingIntExample {
	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1, 2, 3, 4);
		Double result = list.stream().collect(Collectors.averagingInt(v -> v * 2));
		System.out.println(result);
	}
}
