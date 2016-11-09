package com.cs.test.week11.bufferpool3;

import java.nio.ByteBuffer;
import java.util.LinkedList;

public class BufferQueue {
	private final long total;
	private final LinkedList<ByteBuffer> items = new LinkedList<ByteBuffer>();
	//初始化长度
	public BufferQueue(long capacity) {
		this.total = capacity;
	}
	
	//返回list长度
	public long getTotalSize(){
		return this.items.size();
	}
	
	//入队列
	public void put(ByteBuffer buffer) {
		this.items.offer(buffer);
		if (items.size() > total) {
			throw new java.lang.RuntimeException();

		}
	}

	//出队列
	public ByteBuffer poll() {
		return items.poll();
	}

	//是否为空
	public boolean isEmpty() {
		return items.isEmpty();
	}
	
}
