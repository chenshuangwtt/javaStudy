package com.cs.test.week7.forkjoin;

import java.io.File;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;



public class ForkjoinTest {
	public static void main(String[] args) throws 
							InterruptedException, ExecutionException {
		ForkJoinPool pool = new ForkJoinPool();
		File homeDir = null; // new File("E:\\eclipse_workspace\\javaStudy\\src\\main\\java\\com\\cs\\test\\week7\\queue");
		if (homeDir == null) {
			homeDir = new File(System.getProperty("user.dir"));
		}
		SearchTextTask task = new SearchTextTask("System",homeDir);
		pool.invoke(task);
		System.out.println(task.get().toString());
	}
}	
