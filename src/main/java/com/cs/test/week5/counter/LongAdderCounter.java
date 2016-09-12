package com.cs.test.week5.counter;

import java.util.concurrent.atomic.LongAdder;

public class LongAdderCounter  implements Counter{
	LongAdder counter = new LongAdder();

	@Override
	public void increment() {
		counter.increment();
	}

	@Override
	public long getCounter() {
		return counter.longValue();
	}
}
