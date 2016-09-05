package com.cs.test.week4.function;

import java.util.function.Function;

public class Student {
	public String name;
	public int age;

	public Student(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String customShow(Function<Student, String> fun) {
		return fun.apply(this);
	}
}