package com.cs.test.week10;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ByteBufferTest {
	public static void main(String[] args) throws IOException {
		// 创建一个capacity为256的ByteBuffer
		ByteBuffer byteBuffer = ByteBuffer.allocate(256);
		while (true) {
			// 从标准输入流读入一个字符
			int c = System.in.read();
			// 当读到输入流结束时，退出循环
			if (c == -1)
				break;
			// 把读入的字符写入ByteBuffer中
			byteBuffer.put((byte) c);
			// 当读完一行时，输入收集的字符
			if (c == '\n') {
				// 调用flip()使limit变为当前的position的值，position变为0
				// 为接下来从ByteBuffer读取做准备
				byteBuffer.flip();
				// 构建一个byte数组
				byte[] content = new byte[byteBuffer.limit()];
				// 从ByteBuffer中读取数据到byte数组中
				byteBuffer.get(content);
				// 把byte数组的内容写到标准输出
				System.out.println(new String(content));
				// 调用clear()使position变为0,limit变为capacity的值，
				// 为接下来写入数据到ByteBuffer中做准备
				byteBuffer.clear();
			}

		}
	}
}
