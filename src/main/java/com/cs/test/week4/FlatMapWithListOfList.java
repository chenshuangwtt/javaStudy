package com.cs.test.week4;

import java.util.Arrays;
import java.util.List;

public class FlatMapWithListOfList {
	public static void main(String[] args) {
		List<Book> list1 = Arrays.asList(new Book(10, "AAA"), new Book(20, "BBB"));
    	List<Book> list2 = Arrays.asList(new Book(30, "XXX"), new Book(15, "ZZZ"));
    	List<List<Book>> finalList = Arrays.asList(list1, list2);
    	//找出最便宜的书
    	Book book = finalList.stream().flatMap(list -> list.stream()).min(new BookComparator()).get();
    	System.out.println("Name:"+book.getName()+", Price:"+ book.getPrice() );
	}
}
