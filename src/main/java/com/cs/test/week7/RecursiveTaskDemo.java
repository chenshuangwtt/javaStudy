package com.cs.test.week7;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * compute() in Java RecursiveTask This is the main method that performs the
 * computation in RecursiveTask .
 * 
 * exec() in Java RecursiveTask exec() returns Boolean values and implements
 * execution conventions.
 * 
 * getRawResult() in Java RecursiveTask This method returns the result which is
 * actually returned by ForkJoinTask.join(). The use of getRawResult() is for
 * debugging only.
 * 
 * setRawResult(V value) in Java RecursiveTask This method forces the arguments
 * value to be returned by getRawResult(). This methd should be used for only
 * this purpose.
 * 
 */
public class RecursiveTaskDemo {
	public static void main(String[] args) {
		FibonacciCal fibonacciCal = new FibonacciCal(12);
		ForkJoinPool pool = new ForkJoinPool();
		int i = pool.invoke(fibonacciCal);
		System.out.println(i);
	}
}

class FibonacciCal extends RecursiveTask<Integer> {

	private static final long serialVersionUID = 1L;
	final int num;

	FibonacciCal(int num) {
		this.num = num;
	}

	@Override
	protected Integer compute() {
		if (num <= 1) {
			return num;
		}
		FibonacciCal fCal1 = new FibonacciCal(num - 1);
		fCal1.fork();
		FibonacciCal fCal2 = new FibonacciCal(num - 2);
		return fCal2.compute() + fCal1.join();
	}
}
