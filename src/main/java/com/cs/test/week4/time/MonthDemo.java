package com.cs.test.week4.time;

import java.time.Month;

/**
 * Month is an enum and represents the complete months of the year. Find the
 * uses of Month enum
 *
 */
public class MonthDemo {
	public static void main(String[] args) {
		System.out.println(Month.MARCH);
		System.out.println(Month.MARCH.getValue());
		System.out.println(Month.of(3));
		System.out.println(Month.valueOf("MARCH"));
	}
}
