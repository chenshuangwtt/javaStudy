package com.cs.test.week4.parallel;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Collection.stream() returns a sequential stream instance for calling
 * collection. To understand Sequential stream, I am calculating the sum of
 * salary for a given predicate.
 *
 */
public class SequentialStreamDemo {
	public static void main(String[] args) {
		List<Employee> list = new ArrayList<>();
		list.add(new Employee(1, "A", 2000));
		list.add(new Employee(2, "B", 3000));
		list.add(new Employee(3, "C", 4000));
		list.add(new Employee(4, "D", 5000));

		Predicate<Employee> juniorEmp = e -> e.sal > 1000 && e.sal < 4000;
		int salsum = list.stream().filter(juniorEmp).mapToInt(e -> e.sal).sum();

		System.out.println(salsum);
	}
}
