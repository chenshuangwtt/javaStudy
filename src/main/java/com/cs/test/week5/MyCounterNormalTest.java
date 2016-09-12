package com.cs.test.week5;

public class MyCounterNormalTest {
	public static void main(String[] args) {
		final MyCounterNormal myCounter = new MyCounterNormal();
		for (int i = 0; i < 10; i++) { // 启动10个线程
			new Thread(new Runnable() {
				public void run() {
					for (int j = 0; j < 100000; j++) { // 每个线程调用incr
						myCounter.incr();
					}
					myCounter.getCurValue();
				}
			}).start();
		}

	}
}
