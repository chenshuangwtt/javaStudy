package com.cs.test.week4.time;

import java.time.Year;

/**
 * Year class represents year like 2014. This class plays only with year. We can
 * use it to know leap year or can fetch the current year
 * 
 * @author Administrator
 *
 */
public class YearDemo {
	public static void main(String[] args) {
		System.out.println("Year.now():" + Year.now());
		System.out.println("Year.MAX_VALUE:" + Year.MAX_VALUE);
		System.out.println("Year.isLeap(2014):" + Year.isLeap(2014));
		System.out.println("Year.isLeap(2016):" + Year.isLeap(2016));
	}
}
