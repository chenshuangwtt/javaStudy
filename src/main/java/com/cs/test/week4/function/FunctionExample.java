package com.cs.test.week4.function;

import java.util.function.Function;

/**
 * Function<T,R>
 * Function produces result of given data type and accepts one argument as given data type
 * @author Administrator
 *
 */
public class FunctionExample {
	public static void main(String[] args) {
		Function<Integer, String> ob = f1 -> "Age:" + f1;
		System.out.println(ob.apply(20));
	}
}
