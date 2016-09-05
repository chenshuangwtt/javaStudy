package com.cs.test.week4.time;

import java.time.YearMonth;

/**
 * YearMonth is the class that represents the combination of year and month like
 * 2014-09. This class provides methods to get year and month for a given date
 * string
 * 
 */
public class YearMonthDemo {
	public static void main(String[] args) {
		System.out.println("YearMonth.now():" + YearMonth.now());
		System.out.println("getMonthValue():" + YearMonth.parse("2014-09").getMonthValue());
		System.out.println("getYear():" + YearMonth.parse("2014-09").getYear());
		System.out.println("isLeapYear():" + YearMonth.parse("2014-09").isLeapYear());
	}
}
