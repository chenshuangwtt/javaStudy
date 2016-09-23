package com.cs.test.week6.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Test3 {
	private ArrayList<String> datas = new ArrayList<String>();
	private Lock lock = new ReentrantLock();
	private Condition notFull = lock.newCondition();
	private Condition notEmpty = lock.newCondition();

	public static void main(String[] args) {
		Test3 test = new Test3();
		List<Thread> threads = IntStream.range(1, 10).mapToObj(i -> {
			if (i % 2 == 0) {
				return test.new Producer("consumer " + i);
			} else
				return test.new Consumer("producer " + i);
		}).filter(t -> {
			t.start();
			return true;
		}).collect(Collectors.toList());
		threads.forEach(t -> {
			try {
				t.join();
			} catch (InterruptedException e) {

			}
		});
	}

	class Consumer extends Thread {
		public Consumer(String string) {
			this.setName(string);
		}
		@Override
		public void run() {
			consume();
		}
		private void consume() {
			while (true) {
				lock.lock();
				try {
					while (datas.isEmpty()) {
						try {
							System.out.println("队列空，等待数据");
							notEmpty.await();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					datas.forEach(s -> System.out.println(Thread.currentThread().getName() + "  有数据 " +s));
					datas.clear();
					System.out.println("清空数据");
					notFull.signal();
				} finally {
					lock.unlock();
				}
			}
		}
	}

	class Producer extends Thread {
		public Producer(String string) {
			this.setName(string);
		}
		@Override
		public void run() {
			produce();
		}
		private void produce() {
			while (true) {
				lock.lock();
				try {
					while (datas.size() == 1) {
						try {
							System.out.println("队列满，等待有空余空间");
							notFull.await();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					IntStream.range(0, 1).forEach(i -> datas.add(i + "data"));
					System.out.println(Thread.currentThread().getName() + "*** put *****" + datas.get(0));
					notEmpty.signal();
				} finally {
					lock.unlock();
				}
			}
		}
	}
}