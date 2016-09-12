package com.cs.test.week5.counter;

class SyncCounter implements Counter {
	private long counter = 0;

	@Override
	public synchronized void increment() {
		counter++;
	}

	@Override
	public long getCounter() {
		return counter;
	}
}