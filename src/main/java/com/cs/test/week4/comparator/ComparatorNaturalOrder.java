package com.cs.test.week4.comparator;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Comparator.naturalOrder and Comparator.reverseOrder Comparator.naturalOrder
 * is used with the objects of the class which has implemented Comparable
 * interface. We need do some changes in our Student class.
 * 
 * @author Administrator
 *
 */
public class ComparatorNaturalOrder {
	public static void main(String[] args) {
		List<Student> list = Student.getStudentList();
		Collections.sort(list, Comparator.naturalOrder());
		list.forEach(s -> System.out.println("Name:" + s.getName() + " Age:" + s.getAge()));
		// Using natural reversed order for sorting
		System.out.println("--Using natural reversed order for sorting--");
		Collections.sort(list, Comparator.reverseOrder());
		list.forEach(s -> System.out.println("Name:" + s.getName() + " Age:" + s.getAge()));

	}
}
