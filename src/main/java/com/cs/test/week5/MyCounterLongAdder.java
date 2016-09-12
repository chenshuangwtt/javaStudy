package com.cs.test.week5;

import java.util.stream.LongStream;

public class MyCounterLongAdder {
	private long[] array;

	public MyCounterLongAdder(int length) {
		array = new long[length];
	}

	public void incr() {
		int index = IndexHolder.get();
		long o = array[index];
		array[index] = o + 1;
	}
	public  long  getCurValue(){
		return LongStream.of(array).sum();
	}
}
