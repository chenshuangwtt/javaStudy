package com.cs.test.week6;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 读写锁
 *
 * @author Administrator
 *
 */
public class ReadWriteLockTest4 {
	public static void main(String[] args) {
		final ReadWrite1 rw = new ReadWrite1();
		List<Thread> threads = IntStream.range(1, 10).mapToObj(i -> {
			if (i % 2 == 0) {
				return new Thread() {
					public void run() {
						while (true) {
							rw.read();
						}
					}
				};
			} else
				return new Thread() {
					public void run() {
						while (true) {
							rw.write();
						}
					}
				};
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

/**
 * 读和写要互斥，因此要把它们放在同一个类中
 */
class ReadWrite1 {
	// 共享数据，只能有一个线程写该数据，但可以有多个线程同时读该数据。
	ArrayList<String> datas = new ArrayList<String>();
	ReadWriteLock rwl = new ReentrantReadWriteLock();

	/**
	 * 读数据
	 */
	public void read() {
		rwl.readLock().lock();
		try {
			if (datas.size() == 1) {
				System.out.println(Thread.currentThread().getName() + " be ready to read data!");
				Thread.sleep((long) (Math.random() * 1000));
				System.out.println(Thread.currentThread().getName() + "have read data :" + datas.get(0));
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			rwl.readLock().unlock();
		}
	}

	/**
	 * 写数据
	 *
	 * @param data
	 */
	public void write() {
		rwl.writeLock().lock();
		try {
			if (datas.isEmpty()) {
				System.out.println(Thread.currentThread().getName() + " be ready to write data!");
				Thread.sleep((long) (Math.random() * 1000));
				IntStream.range(0, 1).forEach(i -> datas.add(i + " data"));
				System.out.println(Thread.currentThread().getName() + " have write data: " + datas.get(0));
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			rwl.writeLock().unlock();
		}
	}
}