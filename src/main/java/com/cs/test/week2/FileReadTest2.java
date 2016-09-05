package com.cs.test.week2;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 *	ByteBuffer读取 
 */
public class FileReadTest2 {
	public static void main(String[] args) throws IOException {
		RandomAccessFile aFile = new RandomAccessFile("d:\\salary.txt", "rw");
		FileChannel inChannel = aFile.getChannel();

		// create buffer with capacity of 48 bytes
		ByteBuffer buf = ByteBuffer.allocate(48);
		long start = System.currentTimeMillis();
		int bytesRead = inChannel.read(buf); // read into buffer.
		while (bytesRead != -1) {
			buf.flip(); // make buffer ready for read
			while (buf.hasRemaining()) {
//				System.out.print((char) buf.get()); // read 1 byte at a time
				buf.get();
			}
			buf.clear(); // make buffer ready for writing
			bytesRead = inChannel.read(buf);
		}
		System.err.println("read time cost "+(System.currentTimeMillis()-start));
		aFile.close();
	}
}
