package com.cs.test.week4.time;

import java.time.DayOfWeek;

/**
 * java.time.DayOfWeek is an immutable and thread-safe enum in java 8. DayOfWeek
 * represents the day of the week like MONDAY, TUESDAY etc. DayOfWeek day has a
 * numeric value too. We can get day name by numeric value and can get numeric
 * value by DAY also.
 * 
 */
public class DayOfWeekDemo {
	public static void main(String[] args) {
		System.out.print(DayOfWeek.MONDAY.getValue());
		System.out.println(DayOfWeek.of(1));
		System.out.print(DayOfWeek.THURSDAY.getValue());
		System.out.println(DayOfWeek.of(2));
		System.out.print(DayOfWeek.SUNDAY.getValue());
		System.out.println(DayOfWeek.of(7));
	}
}