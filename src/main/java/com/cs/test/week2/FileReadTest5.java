package com.cs.test.week2;

import java.io.File;
import java.io.FileInputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class FileReadTest5 {
	static final int BUFFER_SIZE = 1024;

	public static void main(String[] args) throws Exception {
		File file = new File("D:\\salary.txt");
		FileInputStream fileInputStream = new FileInputStream(file);
		FileChannel fileChannel = fileInputStream.getChannel();
		MappedByteBuffer byteBuffer = fileChannel.map(
				FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());

		byte[] bytes = new byte[1024];
		int len = (int) file.length();
		long begin = System.currentTimeMillis();
		for (int offset = 0; offset < len; offset += 1024) {
			if (len - offset > BUFFER_SIZE) {
				byteBuffer.get(bytes);
			} else {
				byteBuffer.get();
			}
		}
		long end = System.currentTimeMillis();
		System.err.println("file size "+len);
		System.err.println("time is " + (end - begin));
		fileInputStream.close();
	}
}