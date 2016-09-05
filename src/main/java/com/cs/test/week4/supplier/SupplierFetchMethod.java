package com.cs.test.week4.supplier;

import java.util.function.Supplier;

/**
 * Supplier can fetch a method of a class. Create a supplier that will take
 * class name and its static method. Call Supplier.get() method and we will get
 * the result of method
 *
 */
public class SupplierFetchMethod {
	public static void main(String[] args) {
		Supplier<String> supplier = Item2::getStaticVal;
		String val = supplier.get();
		System.out.println("Calling  Method:" + val);
	}
}
