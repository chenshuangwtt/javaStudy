package com.cs.test.week4.runnable;

import java.util.ArrayList;
import java.util.List;

/**
 * Example 4: Using Function Interface to Iterate Collection with Lambda
 * Expression In java 8 there is java.util.function package introduced. With the
 * help of java.util.function.Function API, we will iterate collection using
 * lambda expression. In Function interface there is apply() method that will be
 * called in user defined function. Suppose we have to create a method for
 * custom print then we will define the method as below
 * 
 */
public class FunctionDemo {
	public static void main(String[] args) {
		List<Student> list = new ArrayList();
		list.add(new Student("Ram", 20));
		list.add(new Student("Shyam", 22));
		list.add(new Student("Kabir", 18));
		for (Student st : list) {
			System.out.println(st.customShow(s -> s.getName() + ": " + s.getAge()));
		}
	}
}