package com.cs.test.week4;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class MethodParameterNamesDemo {
	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		Method[] methods = BookService.class.getDeclaredMethods();
		for (Method method : methods) {
			System.out.println(method.getName());
			System.out.println("-------------");
			Parameter[] parameters = method.getParameters();
			for (Parameter p : parameters) {
				if (p.isNamePresent()) {
					System.out.println(p.getName());
				}
			}
		}

	}
}
