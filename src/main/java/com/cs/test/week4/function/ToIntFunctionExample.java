package com.cs.test.week4.function;

import java.util.function.ToIntFunction;

/**
 * ToIntFunction<T> Accepts an argument of given data type and produces integer
 * value
 */
public class ToIntFunctionExample {
	public static void main(String[] args) {
		ToIntFunction<Integer> ob = f -> f * 123;
		System.out.println(ob.applyAsInt(306));
	}
}
