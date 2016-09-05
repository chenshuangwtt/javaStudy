package com.cs.test.week4.iteration;

import java.util.List;

/**
 * Suppose we have a list of employee and we need to update the salary of each
 * and every employee multiplied by two.
 * 
 */
public class ExternalIterationDemo {
	public static void main(String[] args) {
		List<Employee> list = Employee.getEmpList();
		for (Employee e : list) {
			e.setSal(e.getSal() * 2);
		}
	}
}