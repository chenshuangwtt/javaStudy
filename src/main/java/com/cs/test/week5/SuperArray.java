package com.cs.test.week5;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

class SuperArray {
	private final static int BYTE = 1;
	static Unsafe unsafe = null;
	private long size;
	private long address;
	static {
		try {
			Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
			theUnsafe.setAccessible(true);
			unsafe = (Unsafe) theUnsafe.get(null);
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

	}

	public SuperArray(long size) {
		this.size = size;
		address = unsafe.allocateMemory(size * BYTE);
	}

	public void set(long i, byte value) {
		unsafe.putByte(address + i * BYTE, value);
	}

	public int get(long idx) {
		return unsafe.getByte(address + idx * BYTE);
	}

	public long size() {
		return size;
	}
}