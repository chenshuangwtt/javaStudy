package com.cs.test.week11;

import java.util.Vector;

import org.eclipse.webdav.internal.kernel.utils.Assert;

public class BufferPool {
	/**
	 * The size of each buffer in the pool.
	 */
	private static int BUFFER_SIZE = 8 * 1024;
	/**
	 * The maximum number of buffers in the pool.
	 */
	private static int MAX_BUFFERS = 5;

	/**
	 * The buffer pool.
	 */
	private Vector pool = new Vector(MAX_BUFFERS);
	
	public synchronized byte[] getBuffer() {
		if (pool.isEmpty())
			return new byte[BUFFER_SIZE];
		byte[] buffer = (byte[]) pool.lastElement();
		pool.removeElementAt(pool.size() - 1);
		return buffer;
	}

	public synchronized void putBuffer(byte[] buffer) {
		Assert.isNotNull(buffer);
		Assert.isTrue(buffer.length == BUFFER_SIZE);
		if (pool.size() < MAX_BUFFERS)
			pool.addElement(buffer);
	}
}