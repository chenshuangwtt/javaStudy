package com.cs.test.week4.runnable;

public class CalculatorDemo {
	public static void main(String[] args) {
		Calculator cal = (int a, int b) -> a + b;
		int res = cal.add(5, 6);
		System.out.println(res);
	}
}
