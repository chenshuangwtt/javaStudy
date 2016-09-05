package com.cs.test.week4.function;

import java.util.function.LongToDoubleFunction;

/**
 * LongToDoubleFunction Accepts long data type value and produces double data
 * type value
 * 
 * @author Administrator
 *
 */
public class LongToDoubleFunctionExample {
	public static void main(String[] args) {
		LongToDoubleFunction ob = f -> f * f;
		System.out.println(ob.applyAsDouble(43));
	}
}
