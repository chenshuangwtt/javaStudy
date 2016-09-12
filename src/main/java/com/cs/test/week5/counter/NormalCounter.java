package com.cs.test.week5.counter;

class NormalCounter implements Counter {
	private long counter = 0;

	@Override
	public void increment() {
		counter++;
	}

	@Override
	public long getCounter() {
		return counter;
	}
}