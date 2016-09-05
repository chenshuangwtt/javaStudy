package com.cs.test.week4.function;

import java.util.function.IntFunction;

/**
 * IntFunction<R> Accepts only integer value and produces result of given data
 * type
 * 
 * @author Administrator
 *
 */
public class IntFunctionExample {
	public static void main(String[] args) {
		IntFunction ob = f -> f * f;
		System.out.println(ob.apply(43));
	}
}
