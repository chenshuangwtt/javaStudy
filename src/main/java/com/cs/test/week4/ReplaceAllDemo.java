package com.cs.test.week4;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

public class ReplaceAllDemo {
	public static void main(String[] args) {
		List<Person2> list = new ArrayList<>();
		list.add(new Person2(1, "Mahesh"));
		list.add(new Person2(2, "Ram"));
		list.add(new Person2(3, "Krishna"));
		Consumer<Person2> style = (Person2 p) -> System.out.println("id:" + p.getPid() + ", Name:" + p.getName());
		System.out.println("---Before replaceAll---");
		list.forEach(style);
		UnaryOperator<Person2> unaryOpt = pn -> modifyName(pn);
		list.replaceAll(unaryOpt);
		System.out.println("---After replaceAll---");
		list.forEach(style);
	}

	private static Person2 modifyName(Person2 p) {
		p.setName(p.getName().concat(" -God"));
		return p;
	}
}
