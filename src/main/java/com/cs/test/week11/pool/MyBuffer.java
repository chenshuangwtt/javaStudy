package com.cs.test.week11.pool;

import java.nio.ByteBuffer;

public class MyBuffer implements Comparable<MyBuffer> {

	private ByteBuffer byteBuffer;
	private int size;

	public MyBuffer(int size) {
		this.size = size;
		byteBuffer = ByteBuffer.allocate(size);
	}

	public ByteBuffer getByteBuffer() {
		return this.byteBuffer;
	}

	public int getSize() {
		return size;
	}

	@Override
	public int compareTo(MyBuffer o) {
		return this.size > o.size ? 1 : this.size < o.size ? -1 : 0;
	}

}
