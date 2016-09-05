package com.cs.test.week4.stream;

import java.util.List;
import java.util.function.Predicate;

/**
 * Stream.allMatch We pass predicate as an argument to allMatch method. That
 * predicate is applied to each element of stream and if each and every element
 * satisfies the predicate then it returns true otherwise false.
 * 
 * Stream.anyMatch
 * For anyMatch method we pass predicate as an argument. The element of stream
 * is iterated for this predicate. If any element matches then it returns true
 * otherwise false. 
 * 
 * Stream.noneMatch noneMatch method is a method which takes
 * argument as a predicate and if none of element of stream matches the
 * predicate, then it returns true else false
 * 
 */

public class MatchDemo {
	public static void main(String[] args) {
		Predicate<Employee> p1 = e -> e.id < 10 && e.name.startsWith("A");
		Predicate<Employee> p2 = e -> e.sal < 10000;
		List<Employee> list = Employee.getEmpList();
		// using allMatch
		boolean b1 = list.stream().allMatch(p1);
		System.out.println(b1);
		boolean b2 = list.stream().allMatch(p2);
		System.out.println(b2);
		// using anyMatch
		boolean b3 = list.stream().anyMatch(p1);
		System.out.println(b3);
		boolean b4 = list.stream().anyMatch(p2);
		System.out.println(b4);
		// using noneMatch
		boolean b5 = list.stream().noneMatch(p1);
		System.out.println(b5);

	}
}
