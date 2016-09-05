package com.cs.test.week4.supplier;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * While using with Stream in java 8, we can pass Supplier as an augment to some
 * of Stream methods. In the example we are calling Stream.map() in which we are
 * passing Supplier instance
 * 
 */
public class SupplierWithStream {
	public static void main(String[] args) {
		List<Item2> list = new ArrayList<>();
		list.add(new Item2("AA"));
		list.add(new Item2("BB"));
		list.add(new Item2("CC"));
		Stream<String> names = list.stream().map(Item2::getName);
		names.forEach(n -> System.out.println(n));
	}
}
