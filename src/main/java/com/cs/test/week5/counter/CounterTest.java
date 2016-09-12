package com.cs.test.week5.counter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CounterTest {
	public static void main(String[] args) throws Exception {
		int NUM_OF_THREADS = 10;
		int NUM_OF_INCREMENTS = 1000000;
		ExecutorService service = Executors.newFixedThreadPool(NUM_OF_THREADS);
		Counter counter = new LongAdderCounter(); // 实例化特定的实现类
		long before = System.currentTimeMillis();
		for (int i = 0; i < NUM_OF_THREADS; i++) {
		    service.submit(new CounterClient(counter, NUM_OF_INCREMENTS));
		}
		service.shutdown();
		service.awaitTermination(1, TimeUnit.MINUTES);
		long after = System.currentTimeMillis();
		System.out.println("Counter result: " + counter.getCounter());
		System.out.println("Time passed in ms:" + (after - before));
	}
}
