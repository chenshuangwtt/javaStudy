package com.cs.test.week4.time;

import java.time.OffsetTime;

/**
 * OffsetTime represents time with an offset that can be viewed as
 * hour-minute-second-offset. Find the use of OffsetTime
 */
public class OffsetTimeDemo {
	public static void main(String[] args) {
		OffsetTime offTime = OffsetTime.now();
		System.out.println(offTime.getHour() + " hour");
		System.out.println(offTime.getMinute() + " minute");
		System.out.println(offTime.getSecond() + " second");
	}
}
