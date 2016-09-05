package com.cs.test.week4.time;

import java.time.MonthDay;

/**
 * MonthDay represents the combination of the month and day. This class does not
 * provide year. In the example I am showing some uses and working of MonthDay
 * 
 * @author Administrator
 *
 */
public class MonthDayDemo {
	public static void main(String[] args) {
		MonthDay mday = MonthDay.now();
		System.out.println(mday.getDayOfMonth());
		System.out.println(mday.getMonth());
		System.out.println(mday.atYear(2014));
	}
}
