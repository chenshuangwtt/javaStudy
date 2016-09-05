package com.cs.test.week4;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class RemoveIfDemo {
	public static void main(String[] args) {
		List<Person2> list = new ArrayList<>();
		list.add(new Person2(1, "Mahesh"));
		list.add(new Person2(2, "Ram"));
		list.add(new Person2(3, "Krishna"));

		Consumer<Person2> style = (Person2 p) -> System.out.println("id:" + p.getPid() + ",Name:" + p.getName());
		System.out.println("---Before delete---");
		list.forEach(style);
		int pid = 2;
		Predicate<Person2> personPredicate = p -> p.getPid() == pid;
		list.removeIf(personPredicate);
		System.out.println("---After delete---");
		list.forEach(style);

	}
}
