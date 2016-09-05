package com.cs.test.week4;

import java.util.Comparator;

public class BookComparator implements Comparator<Book> {
	@Override
	public int compare(Book b1, Book b2) {
		if (b1.getPrice() > b2.getPrice()) {
			return 1;
		} else if (b1.getPrice() == b2.getPrice()) {
			return 0;
		} else {
			return -1;
		}
	}
}