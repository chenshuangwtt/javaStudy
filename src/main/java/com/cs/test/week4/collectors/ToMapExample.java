package com.cs.test.week4.collectors;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ToMapExample {
	public static void main(String[] args) {
		Map<String, String> map = Stream.of("AA", "BB", "CC").collect(Collectors.toMap(k -> k, v -> v + v));
		map.forEach((k, v) -> System.out.println("key:" + k + "  value:" + v));
	}
}
