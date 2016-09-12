package com.cs.test.week5;

import java.util.concurrent.atomic.AtomicIntegerArray;

/***
 *  int	addAndGet(int i, int delta) 
          以原子方式将给定值与索引 i 的元素相加。
    boolean	compareAndSet(int i, int expect, int update) 
	         如果当前值 == 预期值，则以原子方式将位置 i 的元素设置为给定的更新值。
	int	decrementAndGet(int i) 
	         以原子方式将索引 i 的元素减 1。
	int	get(int i) 
	          获取位置 i 的当前值。
	int	getAndAdd(int i, int delta) 
	          以原子方式将给定值与索引 i 的元素相加。
	int	getAndDecrement(int i) 
	          以原子方式将索引 i 的元素减 1。
	int	getAndIncrement(int i) 
	          以原子方式将索引 i 的元素加 1。
	int	getAndSet(int i, int newValue) 
	          将位置 i 的元素以原子方式设置为给定值，并返回旧值。
	int	incrementAndGet(int i) 
	          以原子方式将索引 i 的元素加 1。
	void	lazySet(int i, int newValue) 
	          最后将位置 i 的元素设置为给定值。
	int	length() 
	          返回该数组的长度。
	void	set(int i, int newValue) 
	          将位置 i 的元素设置为给定值。
	String	toString() 
	          返回数组当前值的字符串表示形式。
	boolean	weakCompareAndSet(int i, int expect, int update) 
	          如果当前值 == 预期值，则以原子方式将位置 i 的元素设置为给定的更新值。
 *
 */
public class AtomicIntegerArrayExample {

	private static AtomicIntegerArray at = new AtomicIntegerArray(10);
	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < at.length(); i++) {
			at.set(i, 1);
		}
		Thread t1 = new Thread(new AddFive());
		Thread t2 = new Thread(new Increment());
		Thread t3 = new Thread(new InsertArray());
		Thread t4 = new Thread(new Compare());
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t1.join();
		t2.join();
		t3.join();
		t4.join();
		System.out.println("All threads are finished."
				+ "	 AtomicInteger array's values are : ");
		for (int i = 0; i < at.length(); i++) {
			System.out.println(i + "-" + at.get(i));
		}
	}

	static class AddFive implements Runnable {
		public void run() {
			for (int i = 0; i < at.length(); i++) {
				int addFive = at.addAndGet(i, 5);
				System.out.println("Thread " + Thread.currentThread().getId() + " / adding five, at " + i
						+ " position value is " + addFive);
			}
			System.out.println("Thread " + Thread.currentThread().getId() + " / array now is : " + at);
		}
	}

	static class Increment implements Runnable {
		public void run() {
			for (int i = 0; i < at.length(); i++) {
				int add = at.incrementAndGet(i);
				System.out.println("Thread " + Thread.currentThread().getId() + " / increasing, at " + i
						+ " position value is " + add);
			}
			System.out.println("Thread " + Thread.currentThread().getId() + " / array now is " + at);
		}

	}

	static class InsertArray implements Runnable {
		public void run() {
			int[] myArray = new int[3];
			for (int i = 0; i < 3; i++) {
				myArray[i] = 5;
			}
			at = new AtomicIntegerArray(myArray);
			System.out.println("Thread " + Thread.currentThread().getId() + " Inseting new array, array now is " + at);
		}
	}

	static class Compare implements Runnable {
		public void run() {
			for (int i = 0; i < at.length(); i++) {
				boolean isFive = at.compareAndSet(i, 5, 3);
				System.out.println("Thread " + Thread.currentThread().getId() + " / comparing value to 5, result is "
						+ isFive + ", so at " + i + " position value is " + at.get(i));
			}
			System.out.println("Thread " + Thread.currentThread().getId() + " / array now is " + at);
		}
	}
}