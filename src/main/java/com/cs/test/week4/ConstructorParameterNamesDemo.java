package com.cs.test.week4;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;

public class ConstructorParameterNamesDemo {
	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		Constructor<?>[] constructors = BookService.class.getDeclaredConstructors();
		for (Constructor<?> constructor : constructors) {
			System.out.println(constructor.getName());
			System.out.println("-------------");
			Parameter[] parameters = constructor.getParameters();
			for (Parameter p : parameters) {
				if (p.isNamePresent()) {
					System.out.println(p.getName());
				}
			}
		}
	}
}
