package com.cs.test.week7.mapreduce;

public interface Output<T> {
	Output<T> reduce(Output<T> other);

	T getResult();
}
