package com.cs.test.week10.buffer;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ByteBufferTest3 {
	public static void main(String[] args) throws IOException {
		// 创建一个capacity为15的ByteBuffer
		ByteBuffer byteBuffer = ByteBuffer.allocate(15);		
		System.out.println("limit="+byteBuffer.limit()+" capacity="+byteBuffer.capacity()+" position="+byteBuffer.position());
		for(int i=0;i<10;i++){
			byteBuffer.put((byte)i);
		}
		System.out.println("limit="+byteBuffer.limit()+" capacity="+byteBuffer.capacity()+" position="+byteBuffer.position());
		byteBuffer.flip();		// 重置position
		System.out.println("limit="+byteBuffer.limit()+" capacity="+byteBuffer.capacity()+" position="+byteBuffer.position());
		for(int i=0;i<5;i++){
			byteBuffer.put((byte)i);
		}
		System.out.println();
		System.out.println("limit="+byteBuffer.limit()+" capacity="+byteBuffer.capacity()+" position="+byteBuffer.position());
		byteBuffer.flip();
		System.out.println("limit="+byteBuffer.limit()+" capacity="+byteBuffer.capacity()+" position="+byteBuffer.position());
		
		
	}
}
