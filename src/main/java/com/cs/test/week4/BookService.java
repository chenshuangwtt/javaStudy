package com.cs.test.week4;

public class BookService {
	public BookService(Integer bookId, String bookDesc) {
		System.out.println(bookId + ":" + bookDesc);
	}

	public void evaluateBook(String bookName, Integer bookPrice) {
		System.out.println(bookName + ":" + bookPrice);
	}
}