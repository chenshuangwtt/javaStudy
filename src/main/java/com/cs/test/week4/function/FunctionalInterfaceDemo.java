package com.cs.test.week4.function;

public class FunctionalInterfaceDemo {
	public static void main(String[] args) {
		// functional interface with no argument
		Display display = () -> "Functional interface with no argument";
		String s = display.show();
		System.out.println(s);
		// functional interface with one argument
		Multiply multiply = (int num) -> num * 10;
		int res = multiply.multiply(5);
		System.out.println(res);
		// functional interface with two argument
		Add add = (int a, int b) -> a + b;
		int rs = add.addData(15, 20);
		System.out.println(rs);
	}
}
