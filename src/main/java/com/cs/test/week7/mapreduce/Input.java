package com.cs.test.week7.mapreduce;

import java.util.List;

public interface Input<T> {
	boolean shouldBeComputedDirectly();
	Output<T> computeDirectly();
	List<MapReduceTask<T>> split();
}
