package com.cs.test.week7.mapreduce;

import java.util.List;
import java.util.concurrent.RecursiveTask;

public class MapReduceTask<T> extends RecursiveTask<Output<T>> {
	private final Input<T> input;

	public MapReduceTask(Input<T> input) {
		this.input = input;
	}

	@Override
	protected Output<T> compute() {
		if (input.shouldBeComputedDirectly()) {
			return input.computeDirectly();
		}

		List<MapReduceTask<T>> subTasks = input.split();
		for (int i = 0; i < subTasks.size(); i++) {
			subTasks.get(i).fork();
		}

		Output<T> result = subTasks.get(0).compute();
		for (int i = 0; i < subTasks.size(); i++) {
			result = result.reduce(subTasks.get(i).join());
		}
		return result;
	}
}
