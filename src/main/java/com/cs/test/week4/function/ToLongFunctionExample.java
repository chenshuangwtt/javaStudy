package com.cs.test.week4.function;

import java.util.function.ToLongFunction;

/**
 * ToLongFunction<T> Accepts an argument of given data type and produces result
 * of long data type
 */
public class ToLongFunctionExample {
	public static void main(String[] args) {
		ToLongFunction<Integer> ob = f1 -> f1 + 50;
		System.out.println(ob.applyAsLong(306));
	}
}
