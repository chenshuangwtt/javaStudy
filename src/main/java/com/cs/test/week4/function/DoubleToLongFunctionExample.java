package com.cs.test.week4.function;

import java.util.function.DoubleToLongFunction;

/**
 * DoubleToLongFunction Accepts double data type value and returns long data
 * type value.
 *
 */
public class DoubleToLongFunctionExample {
	public static void main(String[] args) {
		DoubleToLongFunction ob = f -> new Double(f * 4.8).longValue();
		System.out.println(ob.applyAsLong(43.7));
	}
}
