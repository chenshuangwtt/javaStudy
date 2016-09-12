package com.cs.test.week5;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

public class StackOverflow {
	public static Unsafe UnSafe;

	public static Unsafe getUnsafe()
			throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
		theUnsafe.setAccessible(true);
		Unsafe unsafe = (Unsafe) theUnsafe.get(null);
		return unsafe;
	}

	/**
	 * use 'byte[index]' form to read 1 byte every time
	 * 
	 * @param buf
	 */
	public static void normalLookup(byte[] buf) {
		for (int i = 0; i < buf.length; ++i) {
			if ((byte) 0 == buf[i]) {
				System.out.println("The 1st '0' is at position : " + i);
				return;
			}
		}
		System.out.println("Not found '0'");
	}

	/**
	 * use Unsafe.getByte to read 1 byte every time directly from the memory
	 * 
	 * @param buf
	 */
	public static void unsafeLookup_1B(byte[] buf) {
		int baseOffset = UnSafe.arrayBaseOffset(byte[].class);
		for (int i = 0; i < buf.length; ++i) {
			byte b = UnSafe.getByte(buf, (long) (baseOffset + i));
			if (0 == ((int) b & 0xFF)) {
				System.out.println("The 1st '0' is at position : " + i);
				return;
			}

		}
		System.out.println("Not found '0'");
	}

	/**
	 * use Unsafe.getLong to read 8 byte every time directly from the memory
	 * 
	 * @param buf
	 */
	public static void unsafeLookup_8B(byte[] buf) {
		int baseOffset = UnSafe.arrayBaseOffset(byte[].class);

		// The first (numLongs * 8) bytes will be read by Unsafe.getLong in
		// below loop
		int numLongs = buf.length / 8;
		long currentOffset = 0L;
		for (int i = 0; i < numLongs; ++i) {
			currentOffset = baseOffset + (i * 8); // the step is 8 bytes
			long l = UnSafe.getLong(buf, currentOffset);
			// Compare each byte(in the 8-Byte long) to 0
			// PS:x86 cpu is little-endian mode
			if (0L == (l & 0xFF)) {
				System.out.println("The 1st '0' is at position : " + (i * 8));
				return;
			}
			if (0L == (l & 0xFF00L)) {
				System.out.println("The 1st '0' is at position : " + (i * 8 + 1));
				return;
			}
			if (0L == (l & 0xFF0000L)) {
				System.out.println("The 1st '0' is at position : " + (i * 8 + 2));
				return;
			}
			if (0L == (l & 0xFF000000L)) {
				System.out.println("The 1st '0' is at position : " + (i * 8 + 3));
				return;
			}
			if (0L == (l & 0xFF00000000L)) {
				System.out.println("The 1st '0' is at position : " + (i * 8 + 4));
				return;
			}
			if (0L == (l & 0xFF0000000000L)) {
				System.out.println("The 1st '0' is at position : " + (i * 8 + 5));
				return;
			}
			if (0L == (l & 0xFF000000000000L)) {
				System.out.println("The 1st '0' is at position : " + (i * 8 + 6));
				return;
			}
			if (0L == (l & 0xFF00000000000000L)) {
				System.out.println("The 1st '0' is at position : " + (i * 8 + 7));
				return;
			}
		}

		// If some rest bytes exists
		int rest = buf.length % 8;
		if (0 != rest) {
			currentOffset = currentOffset + 8;
			// Because the length of rest bytes < 8,we have to read them one by
			// one
			for (; currentOffset < (baseOffset + buf.length); ++currentOffset) {
				byte b = UnSafe.getByte(buf, (long) currentOffset);
				if (0 == ((int) b & 0xFF)) {
					System.out.println("The 1st '0' is at position : " + (currentOffset - baseOffset));
					return;
				}
			}
		}
		System.out.println("Not found '0'");
	}

	public static void main(String[] args)
			throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		UnSafe = getUnsafe();

		int len = 1024 * 1024 * 1024; // 1G
		long startTime = 0L;
		long endTime = 0L;

		System.out.println("initialize data...");
		byte[] byteArray1 = new byte[len];
		for (int i = 0; i < len; ++i) {
			byteArray1[i] = (byte) (i % 128 + 1); // No byte will equal to 0
		}
		// If you want to set one byte to 0,uncomment the below statement
		// byteArray1[2500] = (byte)0;
		System.out.println("initialize data done!");

		System.out.println("use normalLookup()...");
		startTime = System.nanoTime();
		normalLookup(byteArray1);
		endTime = System.nanoTime();
		System.out.println("time : " + ((endTime - startTime) / 1000) + " us.");

		System.out.println("use unsafeLookup_1B()...");
		startTime = System.nanoTime();
		unsafeLookup_1B(byteArray1);
		endTime = System.nanoTime();
		System.out.println("time : " + ((endTime - startTime) / 1000) + " us.");

		System.out.println("use unsafeLookup_8B()...");
		startTime = System.nanoTime();
		unsafeLookup_8B(byteArray1);
		endTime = System.nanoTime();
		System.out.println("time : " + ((endTime - startTime) / 1000) + " us.");
	}
}