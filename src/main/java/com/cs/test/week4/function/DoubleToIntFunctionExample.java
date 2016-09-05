package com.cs.test.week4.function;

import java.util.function.DoubleToIntFunction;

/**
 * DoubleToIntFunction Accepts double data type value and returns integer value
 * 
 * @author Administrator
 *
 */
public class DoubleToIntFunctionExample {
	public static void main(String[] args) {
		DoubleToIntFunction ob = f -> new Double(f * 4.8).intValue();
		System.out.println(ob.applyAsInt(43.7));
	}
}
