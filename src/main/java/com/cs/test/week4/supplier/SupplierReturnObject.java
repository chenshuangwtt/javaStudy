package com.cs.test.week4.supplier;

import java.util.function.Supplier;

/**
 * Supplier can create the object of a class. Pass class name and new keyword
 * while creating supplier. Call Supplier.get() and we will get object of that
 * class
 * 
 */
public class SupplierReturnObject {
	public static void main(String[] args) {
		Supplier<Item2> supplier = Item2::new;
		Item2 item = supplier.get();
		System.out.println(item.getMsg());
	}
}
