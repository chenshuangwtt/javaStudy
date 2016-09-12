package com.cs.test.week5;

public class SuperArrayTest {
	public static void main(String[] args) {
		int  sum =0;
		long SUPER_SIZE = (long)Integer.MAX_VALUE * 2;
		SuperArray array = new SuperArray(SUPER_SIZE);
		System.out.println("Array size:" + array.size()); // 4294967294
		for (int i = 0; i < 100; i++) {
		    array.set((long)Integer.MAX_VALUE + i, (byte)3);
		    sum += array.get((long)Integer.MAX_VALUE + i);
		}
		
		
		
		
	}
}
