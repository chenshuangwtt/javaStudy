package com.cs.test.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 类AtomicIntegerWithLock是线程安全的，此结构中大量使用了Lock对象的lock/unlock方法对。
 * 同样可以看到的是对于自增和自减操作使用了++/--。之所以能够保证线程安全，是因为Lock对象的lock()方法保证了只有一个线程能够只有此锁。
 * 需要说明的是对于任何一个lock()方法，都需要一个unlock()方法与之对于，通常情况下为了保证unlock方法总是能够得到执行，
 * unlock方法被置于finally块中
 * 
 * 
 * void lock();
 * 获取锁。
 * 如果锁不可用，出于线程调度目的，将禁用当前线程，并且在获得锁之前，该线程将一直处于休眠状态。
 * 
 * void lockInterruptibly() throws InterruptedException;
 * 如果当前线程未被中断，则获取锁。
 * 如果锁可用，则获取锁，并立即返回。
 * 如果锁不可用，出于线程调度目的，将禁用当前线程，并且在发生以下两种情况之一以前，该线程将一直处于休眠状态：
 * 锁由当前线程获得；或者 其他某个线程中断当前线程，并且支持对锁获取的中断。 如果当前线程：
 * 
 * 在进入此方法时已经设置了该线程的中断状态；或者 在获取锁时被中断，并且支持对锁获取的中断， 则将抛出
 * InterruptedException，并清除当前线程的已中断状态。 Condition newCondition();
 * 返回绑定到此 Lock 实例的新 Condition 实例。下一小节中会重点谈Condition，此处不做过多的介绍。
 * 
 * boolean tryLock();
 * 仅在调用时锁为空闲状态才获取该锁。
 * 如果锁可用，则获取锁，并立即返回值 true。如果锁不可用，则此方法将立即返回值 false。
 * 通常对于那些不是必须获取锁的操作可能有用。
 * 
 * boolean tryLock(long time, TimeUnit unit) throws InterruptedException;
 * 如果锁在给定的等待时间内空闲，并且当前线程未被中断，则获取锁。
 * 如果锁可用，则此方法将立即返回值 true。如果锁不可用，出于线程调度目的，将禁用当前线程，并且在发生以下三种情况之一前，该线程将一直处于休眠状态：
 * 锁由当前线程获得；或者 其他某个线程中断当前线程，并且支持对锁获取的中断；或者 已超过指定的等待时间 如果获得了锁，则返回值 true。
 * 如果当前线程：
 * 在进入此方法时已经设置了该线程的中断状态；或者 在获取锁时被中断，并且支持对锁获取的中断， 则将抛出
 * InterruptedException，并会清除当前线程的已中断状态。 如果超过了指定的等待时间，则将返回值 false。如果 time 小于等于
 * 0，该方法将完全不等待。
 * 
 * void unlock();
 * 释放锁。对应于lock()、tryLock()、tryLock(xx)、lockInterruptibly()等操作，
 * 如果成功的话应该对应着一个unlock()，这样可以避免死锁或者资源浪费。
 */
public class AtomicIntegerWithLock {
	private int value;
	private Lock lock = new ReentrantLock();

	public AtomicIntegerWithLock() {
		super();
	}

	public AtomicIntegerWithLock(int value) {
		this.value = value;
	}

	public final int get() {
		lock.lock();
		try {
			return value;
		} finally {
			lock.unlock();
		}
	}

	public final void set(int newValue) {
		lock.lock();
		try {
			value = newValue;
		} finally {
			lock.unlock();
		}
	}

	public final int getAndSet(int newValue) {
		lock.lock();
		try {
			int ret = value;
			value = newValue;
			return ret;
		} finally {
			lock.unlock();
		}
	}

	public final boolean compareAndSet(int expect, int update) {
		lock.lock();
		try {
			if (value == expect) {
				value = update;
				return true;
			}
			return false;
		} finally {
			lock.unlock();
		}
	}

	public final int getAndIncrement() {
		lock.lock();
		try {
			return value++;
		} finally {
			lock.unlock();
		}
	}

	public final int getAndDecrement() {
		lock.lock();
		try {
			return value--;
		} finally {
			lock.unlock();
		}
	}

	public final int incrementAndGet() {
		lock.lock();
		try {
			return ++value;
		} finally {
			lock.unlock();
		}
	}

	public final int decrementAndGet() {
		lock.lock();
		try {
			return --value;
		} finally {
			lock.unlock();
		}
	}

	public String toString() {
		return Integer.toString(get());
	}
}
