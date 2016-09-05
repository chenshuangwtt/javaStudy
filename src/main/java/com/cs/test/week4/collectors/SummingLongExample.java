package com.cs.test.week4.collectors;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//Collectors.summingLong adds all element of stream for long data type.
public class SummingLongExample {
	public static void main(String[] args) {
		List<Long> list = new ArrayList<>();
		list.add((long) 340);
		list.add((long) 240);
		list.add((long) 360);
		long result = list.stream().collect(Collectors.summingLong(l->l));
		System.out.println(result);
	}
}
