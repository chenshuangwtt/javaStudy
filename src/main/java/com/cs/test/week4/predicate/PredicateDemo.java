package com.cs.test.week4.predicate;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * java.util.function.Predicate is a functional interface introduced in java 8.
 * Predicate is used for assigning a lambda expression. The functional interface
 * is test(T t) which returns Boolean value. When we pass the object to this
 * method, it evaluates the object over assigned lambda expression
 * 
 */
public class PredicateDemo {
	public static void main(String[] args) {
		Predicate<Student> maleStudent = s -> s.age >= 20 && "male".equals(s.gender);
		Predicate<Student> femaleStudent = s -> s.age > 15 && "female".equals(s.gender);
		Function<Student, String> maleStyle = s -> "Hi, You are male and age " + s.age;

		Function<Student, String> femaleStyle = s -> "Hi, You are female and age " + s.age;

		Student s1 = new Student(21, "male");
		if (maleStudent.test(s1)) {
			System.out.println(s1.customShow(maleStyle));
		} else if (femaleStudent.test(s1)) {
			System.out.println(s1.customShow(femaleStyle));
		}
	}
}
