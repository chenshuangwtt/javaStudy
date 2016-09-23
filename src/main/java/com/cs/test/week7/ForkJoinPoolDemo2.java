package com.cs.test.week7;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class ForkJoinPoolDemo2 {
	public static void main(String[] args) {
		ForkJoinPool pool = new ForkJoinPool();
		Task2 task2 = new Task2();
		pool.execute(task2);
	}
}

class Task2 extends ForkJoinTask<String> {

	private static final long serialVersionUID = 1L;

	@Override
	public String getRawResult() {
		return null;
	}

	@Override
	protected void setRawResult(String value) {
	}

	@Override
	protected boolean exec() {
		System.out.println("executing exec method.");
		return true;
	}

}
