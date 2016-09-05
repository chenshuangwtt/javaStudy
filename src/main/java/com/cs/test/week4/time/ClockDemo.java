package com.cs.test.week4.time;

import java.time.LocalDate;

/**
 * 1. Clock.systemDefaultZone() : This method returns Clock object. This method
 * returns current instant using best available system clock. It uses default
 * time zone. 
 * 
 * 2. Clock.systemUTC() : This method also returns Clock object. This
 * method returns current instant using UTC time zone.
 * 
 * Also there are different method in Clock that returns clock instance. To use
 * Clock, best way is to use Dependency Injection Framework. In our example we
 * have used Dependency Injection Framework for our demo.
 * 
 * @author Administrator
 *
 */
public class ClockDemo {
	public static void main(String[] args) {
		IClockInjector clockInjector = new SystemDefaultZoneInjector();
		IProcessClock processClock = clockInjector.getDemoClockInstance();
		processClock.process(LocalDate.MAX);

		clockInjector = new SystemUTCInjector();
		processClock = clockInjector.getDemoClockInstance();
		processClock.process(LocalDate.now());
	}
}