package com.cs.test.week5;

public class Reader implements Runnable {
	private MyArray myArray;
	private int num;

	public Reader(MyArray array, int num) {
	        this.myArray = array;
	        this.num = num;
	    }

	@Override
	public void run() {
		for (int i = 0; i < num; i++) {
			myArray.getCurPos();
		}
	}
}
