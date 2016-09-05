package com.cs.test.week4.time;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * java.time.LocalDateTime is an immutable class that represents the combination
 * of local date and local time like 2009-11-06T10:18:30. LocalDateTime does not
 * represent time zone
 * 
 * @author Administrator
 *
 */
public class LocalDateTimeDemo {
	public static void main(String[] args) {
		LocalDateTime localdt1 = LocalDateTime.now();
		System.out.println(localdt1);
		LocalDateTime localdt2 = LocalDateTime.now(Clock.systemDefaultZone());
		System.out.println(localdt2);
		System.out.println(LocalDateTime.now(ZoneId.of("Indian/Cocos")));
		System.out.println(LocalDateTime.now(ZoneId.of("America/Caracas")));
		System.out.println(LocalDateTime.now(ZoneId.of("Pacific/Norfolk")));

	}
}