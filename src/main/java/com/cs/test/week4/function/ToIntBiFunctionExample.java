package com.cs.test.week4.function;

import java.util.function.ToIntBiFunction;

/**
 * ToIntBiFunction<T,U> Accepts two arguments of given data type and produces
 * result of integer data type
 * 
 */
public class ToIntBiFunctionExample {
	public static void main(String[] args) {
		ToIntBiFunction<Integer, Integer> ob = (f1, f2) -> f1 + f2;
		System.out.println(ob.applyAsInt(102, 306));
	}
}
