package com.cs.test.week7.mapreduce;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class MapReduce<T> {

	private final ForkJoinPool pool;

	public MapReduce(int numThreads) {
		this.pool = new ForkJoinPool(numThreads);
	}

	public T execute(Input<T> input) {
		ForkJoinTask<Output<T>> task = new MapReduceTask<>(input);
		Output<T> output = pool.invoke(task);
		return output.getResult();
	}

}
