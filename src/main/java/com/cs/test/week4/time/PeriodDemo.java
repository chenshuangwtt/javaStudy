package com.cs.test.week4.time;

import java.time.LocalDate;
import java.time.Period;

/**
 * Period is the amount of time in different unit like year, month or days. An
 * example of period can be like 1 year 5 month 10 days
 */
public class PeriodDemo {
	public static void main(String[] args) {
		LocalDate start = LocalDate.now();
		System.out.println("Period.between:" + Period.between(start, LocalDate.MAX).getDays());
		System.out.println("Period.ofDays:" + Period.ofDays(5).getDays());
	}
}
