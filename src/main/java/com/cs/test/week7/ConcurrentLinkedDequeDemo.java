package com.cs.test.week7;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * add() and offer() in ConcurrentLinkedDeque As ConcurrentLinkedDeque is
 * unbounded, so there is no difference in add and offer in this context. 
 * 
 * add()will not throw exception because there is always space to add element and offer() will always be successful to add element. 
 * 
 * poll() in ConcurrentLinkedDeque 
 * poll() retrieves and removes the head of ConcurrentLinkedDeque i.e first element .
 * It returns null if there is no element.
 *
 * peek () in ConcurrentLinkedDeque peek() method will return the first
 * element of ConcurrentLinkedDeque but does not remove it. It will return null if there is no element.
 * Element() in ConcurrentLinkedDeque element() method
 * throws exception when there is no element in ConcurrentLinkedDeque otherwise
 * it is same as peek() method.
 * 
 */
public class ConcurrentLinkedDequeDemo {
	static ConcurrentLinkedDeque<String> cld = new ConcurrentLinkedDeque<String>();

	public static void main(String[] args) {
		ExecutorService exService = Executors.newFixedThreadPool(2);
		ThreadOne elementAdd = new ConcurrentLinkedDequeDemo().new ThreadOne();
		ThreadTwo elementGet = new ConcurrentLinkedDequeDemo().new ThreadTwo();
		exService.execute(elementAdd);
		exService.execute(elementGet);
		exService.shutdown();
	}

	class ThreadOne implements Runnable {
		@Override
		public void run() {
			for (int i = 0; i < 5; i++) {
				cld.add("A" + i);
			}
		}
	}

	class ThreadTwo implements Runnable {

		@Override
		public void run() {
			for (int i = 0; i < 5; i++) {
				String s = cld.poll();
				System.out.println("Element received is: " + s);
			}
		}

	}

}
