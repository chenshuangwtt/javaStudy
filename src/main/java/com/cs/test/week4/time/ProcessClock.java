package com.cs.test.week4.time;

import java.time.Clock;
import java.time.LocalDate;

public class ProcessClock implements IProcessClock {
	private Clock clock;

	public ProcessClock(Clock clock) {
		this.clock = clock;
	}

	public void process(LocalDate localDate) {
		if (localDate.isEqual(LocalDate.now(clock))) {
			System.out.println(clock.getZone());
		} else {
			System.out.println("Does not Match");
		}
	}
}