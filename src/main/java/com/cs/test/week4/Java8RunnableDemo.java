package com.cs.test.week4;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Java8RunnableDemo {
	public static void main(String[] args) {
		final List<Book2> list = Arrays.asList(new Book2(1, "Ramayan"), new Book2(2, "Mahabharat"));
		Runnable r1 = () -> list.forEach(Book2::print);
		Thread th1 = new Thread(r1);
		th1.start();
		Runnable r2 = () -> {
			Consumer<Book2> style = (Book2 b) -> System.out
					.println("Book Id:" + b.getId() + ", Book Name:" + b.getName());
			list.forEach(style);
		};
		Thread th2 = new Thread(r2);
		th2.start();
	}
}
