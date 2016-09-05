package com.cs.test.week4.function;

import java.util.function.DoubleFunction;

/**
 * DoubleFunction<R>
 * Accepts only double data type value and returns the result as given data type.
 */

public class DoubleFunctionExample {
	public static void main(String[] args) {
		DoubleFunction<String> df = d -> String.valueOf(d*5.3); 
		System.out.println(df.apply(43.7));
	}
}
