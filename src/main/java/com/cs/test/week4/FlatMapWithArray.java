package com.cs.test.week4;

import java.util.Arrays;

/**
 * 1. Sample Array {{1,2},{3,4},{5,6}} 
 * 2. After flatMap(row ->Arrays.stream(row)) {1,2,3,4,5,6} 
 * 3. After filter(num -> num%2 == 0) {2,4,6}
 *
 */
public class FlatMapWithArray {
	public static void main(String[] args) {
		Integer[][] data = { { 1, 2 }, { 3, 4 }, { 5, 6 } };
		Arrays.stream(data).flatMap(row -> Arrays.stream(row)).filter(num -> num % 2 == 0).forEach(System.out::println);
	}
}
