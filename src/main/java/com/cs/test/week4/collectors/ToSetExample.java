package com.cs.test.week4.collectors;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//Collectors.toSet collects the element as set for the given data type
public class ToSetExample {
	public static void main(String[] args) {
		Set<String> set = Stream.of("AA", "AA", "BB").collect(Collectors.toSet());
		set.forEach(s -> System.out.println(s));
	}
}
