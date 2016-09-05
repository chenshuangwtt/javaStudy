package com.cs.test.week4.time;

import java.time.ZonedDateTime;

public class ZonedDateTimeDemo {
	public static void main(String[] args) {
		System.out.println(ZonedDateTime.now());
		ZonedDateTime zdt = ZonedDateTime.parse("2014-09-12T10:15:30+01:00[Europe/Paris]");
		System.out.println("getDayOfYear:"+zdt.getDayOfYear());
		System.out.println("zdt.getYear():"+zdt.getYear());
	}
}
