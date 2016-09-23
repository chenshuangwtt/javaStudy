package com.cs.test.week6;

import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LockTest2 {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Thread> threads = IntStream.range(1, 10).mapToObj(i -> {
			if (i % 2 == 0) {
				return new Thread(new Runnable() {
					@Override
					public void run() {
						new MyData().set("world" + i);
					}
				});
			} else
				return new Thread(new Runnable() {
					@Override
					public void run() {
						System.out.println(Thread.currentThread().getName() + "读取到的数据为：" + new MyData().get());
					}
				});
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
}

// 数据源
class MyData {
	private static String name = "hello";
	ReadWriteLock rwl = new ReentrantReadWriteLock();

	public String get() {
		rwl.readLock().lock();
		System.out.println(Thread.currentThread().getName() + "--读取数据前：");
		try {
			try {
				Thread.sleep(new Random().nextInt(3000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return name;
		} finally {
			System.out.println(Thread.currentThread().getName() + "--已经读取完");
			rwl.readLock().unlock();
		}

	}

	public void set(String name) {
		rwl.writeLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + ">写入数据前");
			try {
				Thread.sleep(new Random().nextInt(3000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.name = name;
			System.out.println(Thread.currentThread().getName() + ">写完数据");
		} finally {
			rwl.writeLock().unlock();
		}

	}
}