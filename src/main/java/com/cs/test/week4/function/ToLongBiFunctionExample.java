package com.cs.test.week4.function;

import java.util.function.ToLongBiFunction;

/**
 * ToLongBiFunction<T,U> Accepts two arguments of given data type and produces
 * long data type value
 */
public class ToLongBiFunctionExample {
	public static void main(String[] args) {
		ToLongBiFunction<Integer, Integer> ob = (f1, f2) -> f1 - f2;
		System.out.println(ob.applyAsLong(306, 405));
	}
}
