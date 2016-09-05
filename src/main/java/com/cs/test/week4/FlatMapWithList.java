package com.cs.test.week4;

import java.util.Arrays;
import java.util.List;

/**
 * Stream of writers. { 
 * {"Mohan", 
 * 	{ {10,"AAA"}, {20,"BBB"} } 
 * }, {"Sohan",
 *  { {30,"XXX"}, {15,"ZZZ"} }
 * } 
 * } 
 * 2. After flatMap(writer ->writer.getBooks().stream()), 
 * find the stream of books.
 *  { {10,"AAA"},
 *  {20,"BBB"}, 
 *  {30,"XXX"}, 
 *  {15,"ZZZ"} } 
 * 3. After max(new BookComparator()), find the book with maximum price. 
 * {30,"XXX"}
 * 
 * @author Administrator
 *
 */

public class FlatMapWithList {
	public static void main(String[] args) {
		List<Book> books = Arrays.asList(new Book(10, "AAA"), new Book(20, "BBB"));
		Writer w1 = new Writer("Mohan", books);
		books = Arrays.asList(new Book(30, "XXX"), new Book(15, "ZZZ"));
		Writer w2 = new Writer("Sohan", books);
		List<Writer> writers = Arrays.asList(w1, w2);
		Book book = writers.stream().flatMap(writer -> writer.getBooks().stream()).max(new BookComparator()).get();
		System.out.println("Name:" + book.getName() + ", Price:" + book.getPrice());
	}
}
