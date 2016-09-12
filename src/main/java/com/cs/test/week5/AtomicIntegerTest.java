package com.cs.test.week5;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {
	public static AtomicInteger race = new AtomicInteger(0);

	public static void increase() {
		race.incrementAndGet();
	}

	public static void  getValue(){
		race.get();
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			Thread a = new Thread(new Runnable() {
				public void run() {
					for (int i = 0; i < 10000; i++) {
						increase();
					}
				}
			});
		}
	}

}
