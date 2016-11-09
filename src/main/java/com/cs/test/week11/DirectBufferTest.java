package com.cs.test.week11;

import java.nio.ByteBuffer;
import java.util.Arrays;

import sun.nio.ch.DirectBuffer;

public class DirectBufferTest {
	public static void sleep(long i) {
		try {
			Thread.sleep(i);
		} catch (Exception e) {
			/* skip */
		}
	}
	public static void main(String[] args) throws Exception {
//		ByteBuffer buffer = ByteBuffer.allocateDirect(1024 * 1024 * 200);
//		System.out.println("start");
//		sleep(10000);
//		clean(buffer);
//		System.out.println("end");
//		sleep(10000);
		
		char[] c={73,76,79,86,69,85}; 
		System.out.println(Arrays.toString(c));
	}

	public static void clean(final ByteBuffer byteBuffer) {
		if (byteBuffer.isDirect()) {
			((DirectBuffer) byteBuffer).cleaner().clean();
		}
	}
}
