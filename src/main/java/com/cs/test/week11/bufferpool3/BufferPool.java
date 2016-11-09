package com.cs.test.week11.bufferpool3;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory;

public final class BufferPool {
	private static final Logger LOGGER = LoggerFactory.getLogger(BufferPool.class);
	private final int chunkSize;
	private final ConcurrentLinkedQueue<ByteBuffer> items = new ConcurrentLinkedQueue<ByteBuffer>();
	private long sharedOptsCount;
	private int newCreated;
	private final long capactiy;

	public BufferPool(long bufferSize, int chunkSize) {
		this.chunkSize = chunkSize;
		long size = bufferSize / chunkSize;
		size = (bufferSize % chunkSize == 0) ? size : size + 1;
		this.capactiy = size;
		for (long i = 0; i < capactiy; i++) {
			items.offer(createDirectBuffer(chunkSize));
		}
	}

	public int getChunkSize() {
		return chunkSize;
	}

	public long getSharedOptsCount() {
		return sharedOptsCount;
	}

	public long size() {
		return this.items.size();
	}

	public long capacity() {
		return capactiy + newCreated;
	}


	public ByteBuffer allocate() {
		ByteBuffer node = null;
		node = items.poll();
		if (node == null) {
			try{
				node = this.createDirectBuffer(chunkSize);
				++newCreated;
			}catch(final OutOfMemoryError oom){
				node = this.createTempBuffer(chunkSize);
			}
		}
		return node;
	}

	private boolean checkValidBuffer(ByteBuffer buffer) {
		if (buffer == null || !buffer.isDirect()) {
			return false;
		} else if (buffer.capacity() != chunkSize) {
			return false;
		}
		buffer.clear();
		return true;
	}



	public void recycle(ByteBuffer buffer) {
		if (!checkValidBuffer(buffer)) {
			return;
		}
		sharedOptsCount++;
		items.offer(buffer);
	}

	public boolean testIfDuplicate(ByteBuffer buffer) {
		for (ByteBuffer exists : items) {
			if (exists == buffer) {
				return true;
			}
		}
		return false;
	}
	
	private ByteBuffer createTempBuffer(int size) {
		return ByteBuffer.allocate(size);
	}
	
	private ByteBuffer createDirectBuffer(int size) {
		return ByteBuffer.allocateDirect(size);
	}
	
	public ByteBuffer allocate(int size) {
		if (size <= this.chunkSize) {
			return allocate();
		} else {
			return createTempBuffer(size);
		}
	}

	public static void main(String[] args) {
		BufferPool pool = new BufferPool(1024 * 5, 1024);
		long i = pool.capacity();
		ArrayList<ByteBuffer> all = new ArrayList<ByteBuffer>();
		for (long j = 0; j <= i; j++) {
			all.add(pool.allocate());
		}
		for (ByteBuffer buf : all) {
			pool.recycle(buf);
		}
		System.out.println(pool.size());
	}
}
