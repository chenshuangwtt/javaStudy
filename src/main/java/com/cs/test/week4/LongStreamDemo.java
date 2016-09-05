package com.cs.test.week4;

import java.util.stream.LongStream;

public class LongStreamDemo {
	public static void main(String[] args) {
		System.out.println("--Using LongStream.rangeClosed--");
		LongStream.rangeClosed(13, 15).map(n -> n * n).forEach(s -> System.out.print(s + " "));
		System.out.println("\n--Using LongStream.range--");
		LongStream.range(13, 15).map(n -> n * n).forEach(s -> System.out.print(s + " "));
		System.out.println("\n--Sum of range 1 to 10--");
		System.out.print(LongStream.rangeClosed(1, 10).sum());
		System.out.println("\n--Sorted number--");
		LongStream.of(13, 4, 15, 2, 8).sorted().forEach(s -> System.out.print(s + " "));
	}
}