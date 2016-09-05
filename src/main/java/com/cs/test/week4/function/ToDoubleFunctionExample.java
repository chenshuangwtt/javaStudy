package com.cs.test.week4.function;

import java.util.function.ToDoubleFunction;

/**
 * ToDoubleFunction<T> Accepts the value of given data type and produces double
 * value
 * 
 */
public class ToDoubleFunctionExample {
	public static void main(String[] args) {
		ToDoubleFunction<Integer> ob = f1 -> f1 * 301;
		System.out.println(ob.applyAsDouble(102));
	}
}
