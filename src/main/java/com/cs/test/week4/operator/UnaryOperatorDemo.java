package com.cs.test.week4.operator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;

/**
 * java.util.function.UnaryOperator is a java 8 functional interface that
 * extends java.util.function.Function. UnaryOperator is used to work on a
 * single operand. It returns the same type as an operand. UnaryOperator can be
 * used as lambda expression to pass as an argument. While defining
 * UnaryOperator, we need to define Function.apply(Object) where Function will
 * be the instance of UnaryOperator
 * 
 * @author Administrator
 *
 */
public class UnaryOperatorDemo {
	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(10, 20, 30, 40, 50);
		UnaryOperator<Integer> unaryOpt = i -> i * i;
		unaryOperatorFun(unaryOpt, list).forEach(x -> System.out.println(x));
	}

	private static List<Integer> unaryOperatorFun(UnaryOperator<Integer> unaryOpt, List<Integer> list) {
		List<Integer> uniList = new ArrayList<>();
		list.forEach(i -> uniList.add(unaryOpt.apply(i)));
		return uniList;
	}
}
