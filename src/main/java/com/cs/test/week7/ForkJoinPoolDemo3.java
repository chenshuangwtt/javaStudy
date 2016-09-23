package com.cs.test.week7;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * Using ForkJoinPool.invoke() in Java ForkJoinPool invokes the ForkJoinTask and
 * its sub class RecursiveAction and returns the task on its completion. Find
 * the example to run RecursiveAction by using invoke() method.
 * 
 * Using ForkJoinPool.execute() in Java execute() method in ForkJoinPool
 * executes the ForkJoinTask or Runnable task. ForkJoinPool may execute it in
 * calling thread or any new thread or thread from the pool. Tasks are executed
 * asynchronously.
 * 
 * Using ForkJoinPool.submit() in Java submit() method submits the task to run.
 * The task can be ForkJoinTask or Callable.
 *
 * ForkJoinPool.getStealCount() Suppose there are two threads which has queue of
 * task to perform. It is possible that one thread will steal the task from the
 * queue of another thread. It provides the estimated steal count.
 * 
 * ForkJoinPool.getQueuedTaskCount() It returns the estimated number of task
 * which is in queue held by worker thread. This value will not be exact value,
 * only approximate value.
 * 
 * ForkJoinPool.getParallelism() It returns the number
 * of targeted parallelism which has been provided while instantiating
 * ForkJoinPool . If we have not provided any number the it will return the
 * default number that is Runtime.getRuntime().availableProcessors().
 * 
 * ForkJoinPool.isQuiescent() It provides a Boolean value and checks if all
 * threads are idle. If all threads are idle, it will return true.
 *
 * 
 */
public class ForkJoinPoolDemo3 {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ForkJoinPool pool = new ForkJoinPool();
		Task3 task = new Task3();
		ForkJoinTask<String> output = pool.submit(task);
		System.out.println(output.get());
	}
}

class Task3 implements Callable<String> {
	@Override
	public String call() throws Exception {
		Thread.sleep(1000);
		return "task completed";
	}

}
