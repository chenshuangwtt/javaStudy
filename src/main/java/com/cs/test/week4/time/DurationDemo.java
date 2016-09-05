package com.cs.test.week4.time;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * java.time.Duration is an amount of time. For example 20 minute is an amount
 * of time that can be represented by Duration class.
 *
 */
public class DurationDemo {
	public static void main(String[] args) {
		Duration duration = Duration.of(2, ChronoUnit.DAYS);
		System.out.println(duration.getSeconds());
		System.out.println(Duration.ofHours(1).getSeconds());
		System.out.println(Duration.ofDays(1).getSeconds());
	}
}