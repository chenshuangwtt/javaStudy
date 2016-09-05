package com.cs.test.week4.time;

import java.time.Clock;
import java.time.LocalTime;
import java.time.ZoneId;

/**
 * java.time.LocalTime is a time without time- zone that can be represented like
 * hour-minute-second. LocalTime is immutable and represents only time. It does
 * not represent date or time zone.
 * 
 * @author Administrator
 *
 */
public class LocalTimeDemo {
	public static void main(String[] args) {
		LocalTime localt1 = LocalTime.now();
		System.out.println(localt1);
		LocalTime localt2 = LocalTime.now(Clock.systemDefaultZone());
		System.out.println(localt2);
		System.out.println(LocalTime.now(ZoneId.of("Indian/Cocos")));
		System.out.println(LocalTime.now(ZoneId.of("America/Caracas")));
		System.out.println(LocalTime.now(ZoneId.of("Pacific/Norfolk")));
	}
}