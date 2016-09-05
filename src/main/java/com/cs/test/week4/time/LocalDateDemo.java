package com.cs.test.week4.time;

import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;

/**
 * java.time.LocalDate is an immutable class that represents local date like
 * YYYY-MM-dd. This class does not store time and time zone
 * 
 */
public class LocalDateDemo {
	public static void main(String[] args) {
		LocalDate localDate1 = LocalDate.now();
		System.out.println(localDate1);
		LocalDate localDate2 = LocalDate.now(Clock.systemDefaultZone());
		System.out.println(localDate2);
		System.out.println(LocalDate.now(ZoneId.of("Indian/Cocos")));
		System.out.println(LocalDate.now(ZoneId.of("America/Caracas")));
		System.out.println(LocalDate.now(ZoneId.of("Pacific/Norfolk")));
	}
}