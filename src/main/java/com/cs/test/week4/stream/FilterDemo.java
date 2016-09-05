package com.cs.test.week4.stream;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Stream.filter filters the calling stream on the basis of given predicate.
 * In the example we have an employee list and created a predicate .
 * This predicate will be passed to filter as an argument. 
 * Finally we will print the filtered  stream
 *
 */
public class FilterDemo {
	public static void main(String[] args) {
		List<Employee> list = Employee.getEmpList();
		Predicate<Employee> filterPredicate = e -> e.id > 1 && e.sal < 6000;
		Consumer<Employee> printConsumer = e -> System.out.println(e.id + ", " + e.sal);
		list.stream().filter(filterPredicate).forEach(printConsumer);
	}
}
