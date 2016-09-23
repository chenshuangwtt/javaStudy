package com.cs.test.week7;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ForkJoinPoolDemo {
	public static void main(String[] args) {
		ForkJoinPool pool = new ForkJoinPool();
		Task task = new Task();
		pool.invoke(task);
		System.out.println(pool.getActiveThreadCount());
	}
}

class Task extends RecursiveAction {
	private static final long serialVersionUID = 1L;

	@Override
	protected void compute() {
		System.out.println("Inside Compute method");
	}
}
