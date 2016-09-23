package com.cs.test.week6;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestOnly2 {
	static Object lock = new Object();
	static ArrayList<String> datas = new ArrayList<String>();
	static boolean cflag = false;

	public static void main(String[] args) throws InterruptedException {
		List<Thread> threads = IntStream.range(1, 10).mapToObj(i -> {
			if (i % 2 == 0) {
				return new MThread2("consumer " + i);
			} else
				return new NThread2("producer " + i);
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

class MThread2 extends Thread {
	public MThread2(String string) {
		this.setName(string);
	}
	public void run() {
		while (true) {
			synchronized (TestOnly2.lock) {
				if (TestOnly2.cflag) {
					while (TestOnly2.datas.isEmpty()) {
						System.out.println(Thread.currentThread().getName() + " into wait ,because empty ");
						try {
							TestOnly2.lock.wait();
						} catch (InterruptedException e) {
							break;
						}
						System.out.println(Thread.currentThread().getName() + " wait finished ");
					}
					if (TestOnly2.datas.isEmpty()) {
						System.out.println("impossible empty !! " + Thread.currentThread().getName());
						System.exit(-1);
					}
					TestOnly2.datas.forEach(s -> System.out.println(Thread.currentThread().getName() + "   " + s));
					TestOnly2.datas.clear();
					TestOnly2.cflag = false;
					TestOnly2.lock.notify();
				}
			}
		}
	}
}

class NThread2 extends Thread {
	public NThread2(String string) {
		this.setName(string);
	}
	public void run() {
		while (true) {
			synchronized (TestOnly2.lock) {
				if (!TestOnly2.cflag) {
					while (TestOnly2.datas.size() > 1) {
						System.out.println(Thread.currentThread().getName() + " into wait,because full ");
						try {
							TestOnly2.lock.wait();
						} catch (InterruptedException e) {
							break;
						}
						System.out.println(Thread.currentThread().getName() + "wait finished ");
					}
					if (TestOnly2.datas.size() > 1) {
						System.out.println("impossible full !! " + Thread.currentThread().getName());
						System.exit(-1);
					}
					IntStream.range(0, 1).forEach(i -> TestOnly2.datas.add(i + " data"));
					System.out.println(Thread.currentThread().getName() + "*****************" + TestOnly2.datas.get(0));
					TestOnly2.cflag = true;
					TestOnly2.lock.notify();
				}
			}
		}
	}
}
