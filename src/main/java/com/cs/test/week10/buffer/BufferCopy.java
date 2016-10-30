package com.cs.test.week10.buffer;

import java.nio.CharBuffer;

public class BufferCopy {
	public void myCopyMethod() {
		//缓冲复制
		CharBuffer buffer = CharBuffer.allocate(8);
		buffer.position(3).limit(6).mark().position(5);
		CharBuffer dupeBuffer = buffer.duplicate();
		buffer.clear();

		CharBuffer charBuffer = CharBuffer.allocate(8);
		buffer.position(3).limit(5);
		CharBuffer sliceBuffer = charBuffer.slice();

		char[] myBuffer = new char[100];
		CharBuffer cb = CharBuffer.wrap(myBuffer);
		cb.position(12).limit(21);
		CharBuffer sliced = cb.slice();

	}
}
