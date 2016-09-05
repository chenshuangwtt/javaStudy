package com.cs.test.week4;

import java.util.stream.IntStream;

/**
 * rangeClosed(a,b): The values from a to be are considered by incrementing 1.
 * range(a,b) : Values from a to b-1 are considered. 
 * sum: Calculates the sum of values. 
 * sorted: Values are sorted.
 */
public class IntStreamDemo {
	public static void main(String[] args) {
		System.out.println("--Using IntStream.rangeClosed--");
		IntStream.rangeClosed(13, 15).map(n -> n * n).forEach(s -> System.out.print(s + " "));
		System.out.println("\n--Using IntStream.range--");
		IntStream.range(13, 15).map(n -> n * n).forEach(s -> System.out.print(s + " "));
		System.out.println("\n--Sum of range 1 to 10--");
		System.out.print(IntStream.rangeClosed(1, 10).sum());
		System.out.println("\n--Sorted number--");
		IntStream.of(13, 4, 15, 2, 8).sorted().forEach(s -> System.out.print(s + " "));
	}
}
