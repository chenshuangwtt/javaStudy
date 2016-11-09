package com.cs.test.week11.pool;

import java.nio.ByteBuffer;
import java.util.Random;



public class Test {
	public static void main(String[] args) throws Exception {
		int loop = 1000;
		final int SIZE = 64 * 1024 * 1024;
		long  time = System.currentTimeMillis();
		Random random = new  Random();
		for (int i = 0; i < loop; i++) {
			int size = random.nextInt(SIZE);
			MyBuffer myBuffer = DirectMyBufferPool.allocation(size);
			DirectMyBufferPool.removeBuffer(myBuffer);
		}
		System.out.println("1千次DirectByteBufferPool分配和回收共花费： "+(System.currentTimeMillis()-time));
		time = System.currentTimeMillis();
		for (int i = 0; i < loop; i++) {
			int size = random.nextInt(SIZE);
			ByteBuffer byteBuffer = ByteBuffer.allocate(size);
			byteBuffer = null;
		}
		System.out.println("1千次ByteBuffer分配和回收共花费： "+(System.currentTimeMillis()-time));
		
	}
}
