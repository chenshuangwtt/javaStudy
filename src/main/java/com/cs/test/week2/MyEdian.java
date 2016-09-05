package com.cs.test.week2;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class MyEdian {
	// 方法一
	public int little2Big1(int i) {
		return (i & 0xff) << 24 | (i & 0xff00) << 8 | (i & 0xff000) >> 8 | (i >> 24) & 0xff;
	}

	// 方法二：直接使用api中提供的方法
	public int reverseBytes(int i) {
		return Integer.reverseBytes(i);
	}

	// 方法三： 使用ByteBuffer ，效率更优。
	public static int little2Big(int i) {
		ByteBuffer buffer = ByteBuffer.allocate(Integer.SIZE);
		buffer.order(ByteOrder.LITTLE_ENDIAN);
		buffer.putInt(i);
		buffer.order(ByteOrder.BIG_ENDIAN);
		buffer.rewind();
		return buffer.getInt();
	}
	
	
}
