package com.cs.test.week11.pool;

import java.util.LinkedList;

public class MyBufferSet implements Comparable<MyBufferSet> {
	private LinkedList<MyBuffer> listMyBuffer = null;
	private int capacity = 0;
	private int size = 0;
	private int usedSize = 0;

	public boolean add(MyBuffer myBuffer) {
		if (listMyBuffer == null) {
			listMyBuffer = new LinkedList<MyBuffer>();
			capacity = myBuffer.getSize();
		}
		size++;
		usedSize++;
		listMyBuffer.add(myBuffer);
		return true;
	}

	public int getCapacity() {
		return capacity;
	}

	public MyBuffer getBuffer() {
		MyBuffer buffer;
		if (usedSize < size) {
			buffer = listMyBuffer.get(usedSize++);
		} else {
			buffer = new MyBuffer(capacity);
			listMyBuffer.addLast(buffer);
			usedSize++;
		}
		return buffer;
	}

	public boolean removeBuffer(MyBuffer myBuffer) {
		myBuffer.getByteBuffer().clear();
		listMyBuffer.remove(myBuffer);
		listMyBuffer.addLast(myBuffer);
		usedSize--;
		return true;
	}

	@Override
	public int compareTo(MyBufferSet o) {
		return this.capacity > o.capacity ? 1 : this.capacity < o.capacity ? -1 : 0;
	}

}
