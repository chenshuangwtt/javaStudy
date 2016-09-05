package com.cs.test.week4.function;

import java.util.function.LongToIntFunction;

/**
 * LongToIntFunction Accepts long data type value and produces integer data type
 * value
 * 
 * @author Administrator
 *
 */
public class LongToIntFunctionExample {
	public static void main(String[] args) {
		LongToIntFunction ob = f -> (int) (f * f);
		System.out.println(ob.applyAsInt(43));
	}
}
