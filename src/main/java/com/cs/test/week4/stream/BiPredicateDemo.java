package com.cs.test.week4.stream;

import java.util.function.BiPredicate;

/**
 * java.util.function.BiPredicate is a functional interface which accepts two
 * argument and returns Boolean value. Apply business logic for the values
 * passed as an argument and return the boolean value. BiPredicate functional
 * method is test(Object, Object). Find the simple example for how to use
 * BiPredicate
 * 
 */
public class BiPredicateDemo {
	public static void main(String[] args) {
		BiPredicate<Integer, String> condition = (i, s) -> i > 20 && s.startsWith("R");
		System.out.println(condition.test(10, "Ram"));
		System.out.println(condition.test(30, "Shyam"));
		System.out.println(condition.test(30, "Ram"));
	}
}
