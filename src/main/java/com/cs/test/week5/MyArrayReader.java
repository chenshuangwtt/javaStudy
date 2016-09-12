package com.cs.test.week5;

public class MyArrayReader implements Runnable {
	private MyArray myArray;

	public MyArrayReader(MyArray array) {
		this.myArray = array;
	}

	@Override
	public void run() {
		System.err.println(Thread.currentThread().getName() 
				+ myArray.get());
	}
}
