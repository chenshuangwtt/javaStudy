package com.cs.test.week7;

import java.util.concurrent.Callable;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * ForkJoinTask .adapt
 * It returns a new ForkJoinTask that will be invoked using ForkJoinPool. adapt() method
 * can run callable and runnable threads. We will see one demo here.
 * 
 * ForkJoinTask.complete complete() method completes a task and assigns the
 * result in passed argument of this method. Mainly this method is used to
 * receive the result of asynchronous task. 
 * 
 * ForkJoinTask.getPool This method returns the ForkJoinPool within which the given ForkJoinTask is running.
 * 
 * ForkJoinTask.tryUnfork By its names it is clear that it tries to un fork the
 * task within a ForkJoinPool. A task which has been newly forked and yet has
 * not been stated by any thread within the ForkJoinPool, can be un fork.
 * 
 * ForkJoinTask.isDone This method returns true if task is done by any way
 * either normal termination, or any exception.
 * 
 * @author Administrator
 *
 */
public class ForkJoinTaskDemo {
	public static void main(String[] args) {
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		DemoTask task = new DemoTask();
		ForkJoinTask<String> forkJoinTask = ForkJoinTask.adapt(task);
		forkJoinPool.invoke(forkJoinTask);
		System.out.println(forkJoinTask.isDone());

	}
}

class DemoTask implements Callable<String> {
	@Override
	public String call() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "Task Done";
	}

}
