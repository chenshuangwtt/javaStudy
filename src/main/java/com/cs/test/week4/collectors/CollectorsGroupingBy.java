package com.cs.test.week4.collectors;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * groupingBy is a static method of java.util.stream.Collectors in java 8.
 * groupingBy does the grouping of elements on the basis of any given key and
 * returns a Collector
 * 
 * We have a list of Student class. Grouping is done on the basis of student
 * class name. List is converted into stream of student object. Then call
 * collect method of stream. groupingBy of Collectors class checks each element
 * of stream and gets class name and then group it as list. Finally we get a map
 * where key is the one by which grouping is done
 * 
 * @author Administrator
 *
 */
public class CollectorsGroupingBy {
	public static void main(String[] args) {
		Student3 s1 = new Student3("Ram", "A", 20);
		Student3 s2 = new Student3("Shyam", "B", 22);
		Student3 s3 = new Student3("Mohan", "A", 22);
		Student3 s4 = new Student3("Mahesh", "C", 20);
		Student3 s5 = new Student3("Krishna", "B", 21);
		List<Student3> list = Arrays.asList(s1, s2, s3, s4, s5);
		// Group Student on the basis of ClassName
		System.out.println("----Group Student on the basis of ClassName----");
		Map<String, List<Student3>> stdByClass = list.stream().
				collect(Collectors.groupingBy(Student3::getClassName));
		stdByClass.forEach((k, v) -> System.out.println("Key:" + k + " "
				+ ((List<Student3>) v).stream().map(m -> m.getName())
				.collect(Collectors.joining(","))));
		// Group Student on the basis of age
		System.out.println("----Group Student on the basis of age----");
		Map<Integer, List<Student3>> stdByAge = list.stream()
				.collect(Collectors.groupingBy(Student3::getAge));
		stdByAge.forEach((k, v) -> System.out.println("Key:" + k + "  "
				+ ((List<Student3>) v).stream().map(m -> m.getName())
				.collect(Collectors.joining(","))));

	}
}
