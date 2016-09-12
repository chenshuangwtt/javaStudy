package com.cs.test.week5;

public class MyCounterNormal{
	private volatile long value = 0;

	public  void incr() {
		value ++;
	}

	public  long getCurValue() {
		System.err.println("当前的值为"+value);
		return value;
	}
}
