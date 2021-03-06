package com.cs.test.week7;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class CountTask extends RecursiveTask<Integer> {

	private static final int THRESHOLD = 2; // 阈值
	private int start;
	private int end;

	public CountTask(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	protected Integer compute() {
		int sum = 0;
		// 如果任务足够小就计算任务
		boolean canCompute = (end - start) <= THRESHOLD;
		if (canCompute) {
			for (int i = start; i <= end; i++) {
				sum += i;
			}
		} else {
			// 如果任务大于阈值，就分裂成两个子任务计算
			int middle = (start + end) / 2;
			CountTask leftTask = new CountTask(start, middle);
			CountTask rightTask = new CountTask(middle + 1, end);
			//执行子任务
			leftTask.fork();
			rightTask.fork();
			//等待子任务执行完，并得到其结果
			try {
				int leftResult = leftTask.get();
				int rightResult = rightTask.get();
				//合并子任务
				sum = leftResult + rightResult;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return sum;
	}
	
	public static void main(String[] args) {
		ForkJoinPool  forkJoinPool = new  ForkJoinPool();
		//生成一个计算任务，计算负责1+2+3+4
		CountTask task = new CountTask(1, 4);
		//执行任务
		Future<Integer> result = forkJoinPool.submit(task);
		try {
			System.out.println(result.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
}
