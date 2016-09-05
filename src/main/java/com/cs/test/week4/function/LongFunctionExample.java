package com.cs.test.week4.function;

import java.util.function.LongFunction;

/**
 * LongFunction<R> Accepts only long data type value and produces result of
 * given data type.
 * 
 * @author Administrator
 *
 */
public class LongFunctionExample {
	public static void main(String[] args) {
		LongFunction<String> ob = f -> String.valueOf(f * f);
		System.out.println(ob.apply(43));
	}
}
