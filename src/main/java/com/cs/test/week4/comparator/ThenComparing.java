package com.cs.test.week4.comparator;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ThenComparing {
	public static void main(String[] args) {
		Student s1 = new Student("Shyam", 22);
		Student s2 = new Student("Ram", 22);
		Student s3 = new Student("Mohan", 19);
		List<Student> list = Arrays.asList(s1, s2, s3);
		Comparator<Student> ageComparator = Comparator.comparing(Student::getAge);
		Comparator<Student> nameComparator = Comparator.comparing(Student::getName);
		Collections.sort(list, ageComparator.thenComparing(nameComparator));
		list.forEach(s -> System.out.println("Name:" + s.getName() + " Age:" + s.getAge()));
	}
}
