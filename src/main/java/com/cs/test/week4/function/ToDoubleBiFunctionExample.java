package com.cs.test.week4.function;

import java.util.function.ToDoubleBiFunction;

/**
 * ToDoubleBiFunction<T,U> Accepts two arguments of the given data type and
 * produces result of double data type.
 */
public class ToDoubleBiFunctionExample {
	public static void main(String[] args) {
		ToDoubleBiFunction<Integer, Integer> ob = (f1, f2) -> f1 + f2;
		System.out.println(ob.applyAsDouble(102, 305));
	}
}
