package com.cs.test.week5;

import sun.misc.Unsafe;

public class Writer implements Runnable {
	public static int iceNum = 1;
	public static int copy = 0;
	@Override
	public void run() {
		int count = 0;
		long start = System.currentTimeMillis();
		while (true) {
			iceNum++;
			count++;
			long end = System.currentTimeMillis();
			if (end - start > 3000) {
				Unsafe.getUnsafe().fullFence();
			}
			start = System.currentTimeMillis();
		}
	}
}
