package com.cs.test.week4.time;

import java.time.OffsetDateTime;

/**
 * OffsetDateTime represents all date and time fields. This class represents
 * date and time with an offset. Find the uses of the OffsetDateTime.
 * 
 */
public class OffsetDateTimeDemo {
	public static void main(String[] args) {
		OffsetDateTime offsetDT = OffsetDateTime.now();
		System.out.println(offsetDT.getDayOfMonth());
		System.out.println(offsetDT.getDayOfYear());
		System.out.println(offsetDT.getDayOfWeek());
		System.out.println(offsetDT.toLocalDate());
	}
}