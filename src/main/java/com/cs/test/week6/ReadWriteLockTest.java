package com.cs.test.week6;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ReadWriteLockTest {
	static ReadWriteLock lock = new ReentrantReadWriteLock();
	static ArrayList<String> datas = new ArrayList<String>();

	public static void main(String[] args) throws InterruptedException {
		List<Thread> threads = IntStream.range(1, 10).mapToObj(i -> {
			if (i % 2 == 0) {
				return new WriteThread("consumer " + i);
			} else
				return new ReadThread("producer " + i);
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

class WriteThread extends Thread {
	public WriteThread(String string) {
		this.setName(string);
	}

	public void run() {
		while (true) {
			while (ReadWriteLockTest.datas.isEmpty()) {
				try {
					ReadWriteLockTest.lock.writeLock().lock();
					IntStream.range(0, 1).forEach(i -> ReadWriteLockTest.datas.add(i + "data"));
					System.out.println(
							Thread.currentThread().getName() + "*** put *****" + ReadWriteLockTest.datas.get(0));
				} finally {
					ReadWriteLockTest.lock.writeLock().unlock();
				}
			}
		}
	}
}

class ReadThread extends Thread {
	public ReadThread(String string) {
		this.setName(string);
	}

	public void run() {
		while (true) {
			while (ReadWriteLockTest.datas.size() == 1) {
				try {
					ReadWriteLockTest.lock.readLock().lock();
					ReadWriteLockTest.datas
							.forEach(s -> System.out.println(Thread.currentThread().getName() + "   " + s));
					ReadWriteLockTest.datas.clear();
				} finally {
					ReadWriteLockTest.lock.readLock().unlock();
				}
			}
		}
	}
}
