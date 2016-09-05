package com.cs.test.week4.collectors;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Collectors.partitioningBy accepts predicate that will be defined to return
 * true or false. This predicate is applied on all the elements of stream.
 * Collectors.partitioningBy returns the Collector that will be converted into a
 * map by Stream.collect method. The key of map will be true and false only
 * 
 * @author Administrator
 *
 */
public class CollectorsPartitioningBy {
	public static void main(String[] args) {
		Student2 s1 = new Student2("Ram", 18);
		Student2 s2 = new Student2("Shyam", 22);
		Student2 s3 = new Student2("Mohan", 19);
		Student2 s4 = new Student2("Mahesh", 20);
		Student2 s5 = new Student2("Krishna", 21);
		List<Student2> list = Arrays.asList(s1, s2, s3, s4, s5);
		// partition of Student on the basis of age
		System.out.println("----Partition of Student on the basis of age >20 ----");
		Map<Boolean, List<Student2>> stdByClass = list.stream()
				.collect(Collectors.partitioningBy(s -> s.getAge() > 20));
		stdByClass.forEach((k, v) -> System.out.println("Key:" + k + "  "
				+ ((List<Student2>) v).stream().map(s -> s.getName())
				.collect(Collectors.joining(","))));
	}
}
