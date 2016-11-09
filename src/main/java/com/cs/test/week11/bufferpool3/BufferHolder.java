package com.cs.test.week11.bufferpool3;

import java.nio.ByteBuffer;

public interface BufferHolder {
	public  void hold(ByteBuffer byteBuffer);
	public  void free();
}
