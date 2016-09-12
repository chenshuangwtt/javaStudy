package com.cs.test.week5.counter;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

class CASCounter implements Counter {
	private volatile long counter = 0;
	private Unsafe unsafe;
	private long offset;

	private static Unsafe getUnsafe() {
		try {
			Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
			theUnsafe.setAccessible(true);
			return (Unsafe) theUnsafe.get(null);
		} catch (Exception e) {
			throw new AssertionError(e);
		}
	}

	public CASCounter() throws Exception {
		unsafe = getUnsafe();
		offset = unsafe.objectFieldOffset(CASCounter.class.getDeclaredField("counter"));
	}

	@Override
	public void increment() {
		long before = counter;
		while (!unsafe.compareAndSwapLong(this, offset, before, before + 1)) {
			before = counter;
		}
	}

	@Override
	public long getCounter() {
		return counter;
	}
}