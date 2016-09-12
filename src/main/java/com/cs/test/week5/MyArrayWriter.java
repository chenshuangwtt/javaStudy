package com.cs.test.week5;

import java.util.Random;

public class MyArrayWriter implements Runnable {
	private MyArray myArray;
	private int num;

	public MyArrayWriter(MyArray array, int num) {
        this.myArray = array;
        this.num = num;
    }

	@Override
	public void run() {
		Random random  = new Random();
		for (int i = 0; i < num; i++) {
			myArray.append((byte)random.nextInt());
		}
	}

}
