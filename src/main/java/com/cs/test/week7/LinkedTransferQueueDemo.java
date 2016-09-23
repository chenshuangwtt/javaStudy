package com.cs.test.week7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedTransferQueue;

/**
 * LinkedTransferQueue is unbounded TransferQueue. It means LinkedTransferQueue
 * has no size restriction. This queue follows first-in-first-out (FIFO) concept
 * to get and add element. We will discuss some methods of LinkedTransferQueue.
 * 
 * transfer(E e) : Inherited from TransferQueue in which producer transfers the
 * element to consumer and waits if necessary. 
 * 
 * put(E e) : producers put the element and it does not throw exception because there is no size restriction.
 * LinkedTransferQueue is unbounded. 
 * take() : It retrieves the element from queue and wait if empty.
 * 
 * @author Administrator
 *
 */
public class LinkedTransferQueueDemo {
	static LinkedTransferQueue<String> lnkTransQueue = new LinkedTransferQueue();

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		Producer producer = new LinkedTransferQueueDemo().new Producer();
		Consumer consumer = new LinkedTransferQueueDemo().new Consumer();
		executorService.execute(producer);
		executorService.execute(consumer);
		executorService.shutdown();
	}

	class Producer implements Runnable {
		public void run() {
			for (int i = 0; i < 3; i++) {
				try {
					System.out.println("Producer is waiting to transfer...");
					lnkTransQueue.transfer("A" + i);
					System.out.println("producer transfered element: A" + i);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	class Consumer implements Runnable {
		@Override
		public void run() {
			for (int i = 0; i < 3; i++) {
				try {
					System.out.println("Consumer is waiting to take element...");
					String s = lnkTransQueue.take();
					System.out.println("Consumer received Element: " + s);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
