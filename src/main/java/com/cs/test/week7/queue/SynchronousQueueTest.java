package com.cs.test.week7.queue;

import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueTest {
	public static void main(String[] args) {
		SynchronousQueue<String> queue=new SynchronousQueue();
		if(queue.offer("S1")){
			System.out.println("scucess");
		}else{
			System.out.println("faield");
		}
	}
}
