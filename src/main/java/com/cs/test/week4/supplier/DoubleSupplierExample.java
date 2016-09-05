package com.cs.test.week4.supplier;

import java.util.function.DoubleSupplier;

/**
 * java.util.function.DoubleSupplier is a functional interface that can be used
 * as a Supplier for double values. DoubleSupplier always returns double data
 * type value by the method getAsDouble()
 * 
 */
public class DoubleSupplierExample {
	public static void main(String[] args) {
		Item item = new Item(true, 123);
		DoubleSupplier ds = item::getVal;
		System.out.println("Double Value:" + ds.getAsDouble());
	}
}
