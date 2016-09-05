package com.cs.test.week4.parallel;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.function.Predicate;

/**
 * Collection.parallelStream() returns parallel stream instance for calling
 * collection. The Stream object can be used for different purpose. In this
 * example we have taken a list of employee which has salary field. For a given
 * predicate, we will filter the list and then will calculate the average
 * salary.
 * 
 */
public class ParallelStreamDemo {
	public static void main(String[] args) {
		List<Employee> list = new ArrayList<>();
		list.add(new Employee(1, "A", 2000));
		list.add(new Employee(2, "B", 3000));
		list.add(new Employee(3, "C", 4000));
		list.add(new Employee(4, "D", 5000));

		Predicate<Employee> seniorEmp = e -> e.sal > 3000 && e.sal < 6000;
		OptionalDouble averageSal = list.parallelStream().filter(seniorEmp).mapToDouble(e -> e.sal).average();

		System.out.println(averageSal.getAsDouble());
	}
}
