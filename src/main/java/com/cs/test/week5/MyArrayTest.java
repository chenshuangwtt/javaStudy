package com.cs.test.week5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class MyArrayTest {
	public static void main(String[] args) throws InterruptedException {
		MyArray  myArray = new MyArray(10);
		int NUM_OF_THREADS = 10;
		ExecutorService service = Executors.newFixedThreadPool(NUM_OF_THREADS);
		long before = System.currentTimeMillis();
		for (int i = 0; i < 100; i++) {
		    service.submit(new MyArrayReader(myArray));
		}
		for (int i = 0; i < 1; i++) {
		    service.submit(new MyArrayWriter(myArray,30));
		}
		service.shutdown();
		service.awaitTermination(1, TimeUnit.MINUTES);
		long after = System.currentTimeMillis();
		System.out.println("Time passed in ms:" + (after - before));
		
	}
}	
