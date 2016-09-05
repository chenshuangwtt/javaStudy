package com.cs.test.week4.supplier;

import java.util.function.BooleanSupplier;

/**
 * BooleanSupplier java.util.function.BooleanSupplier is a functional interface
 * that can be used as a Supplier for Boolean values. BooleanSupplier always
 * returns boolean value by the method getAsBoolean()
 * 
 * @author Administrator
 *
 */
public class BooleanSupplierExample {
	public static void main(String[] args) {
		Item item = new Item(true, 123);
		BooleanSupplier bs = item::isStatus;
		System.out.println("Status:" + bs.getAsBoolean());
	}
}
