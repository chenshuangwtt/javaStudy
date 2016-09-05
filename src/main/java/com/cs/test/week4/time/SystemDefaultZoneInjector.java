package com.cs.test.week4.time;

import java.time.Clock;

public class SystemDefaultZoneInjector implements IClockInjector {
	@Override
	public IProcessClock getDemoClockInstance() {
		return new ProcessClock(Clock.systemDefaultZone());
	}
}