package com.cs.test.week4.stream;

import java.util.function.BiFunction;

/**
 * java.util.function.BiFunction is a functional interface. BiFunction accepts
 * two arguments and returns a value. While declaring BiFunction we need to tell
 * what type of argument will be passed and what will be return type. We can
 * apply our business logic with those two values and return the result.
 * BiFunction has functiona method as apply(T t, U u) which accepts two argument
 * 
 */
public class BiFunctionDemo {
	public static void main(String[] args) {
		BiFunction<Integer, Integer, String> biFunction = (num1, num2) -> "Result:" + (num1 + num2);
		System.out.println(biFunction.apply(20, 25));
	}
}
