package com.cs.test.week11.bufferpool3;

import java.nio.ByteBuffer;

public class HeapBufferAllocator implements BufferAllocator {
	private static final HeapBufferAllocator instance = new HeapBufferAllocator();
	protected static final int DEFAULT_BUFFER_SIZE = 64 * 1024;

	private HeapBufferAllocator() {
	}

	public static final HeapBufferAllocator getDefault() {
		return instance;
	}

	@Override
	public void allocate(BufferHolder holder) {
		allocate(holder, DEFAULT_BUFFER_SIZE); 
	}

	@Override
	public void allocate(BufferHolder holder, int bufferSize) {
		ByteBuffer buffer = ByteBuffer.allocate(bufferSize);  
        holder.hold(buffer);  
	}

	@Override
	public void release(BufferHolder holder) {
		holder.free();  
	}
}
