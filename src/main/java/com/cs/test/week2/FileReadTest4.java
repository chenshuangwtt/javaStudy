package com.cs.test.week2;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Scanner;

public class FileReadTest4 {
	public void readResource() {
		long fileLength = 0;
		final int BUFFER_SIZE = 14*500000;// 3M的缓冲
		
		File file = new File("D:\\salary2.txt");
		fileLength = file.length();
		try {
			MappedByteBuffer inputBuffer = new RandomAccessFile(file, "r").getChannel()
					.map(FileChannel.MapMode.READ_ONLY, 0, fileLength);// 读取大文件
			byte[] dst = new byte[BUFFER_SIZE];// 每次读出3M的内容
			long start = System.currentTimeMillis();
			for (int offset = 0; offset < fileLength; offset += BUFFER_SIZE) {
				if (fileLength - offset >= BUFFER_SIZE) {
					for (int i = 0; i < BUFFER_SIZE; i++)
						dst[i] = inputBuffer.get(offset + i);
				} else {
					for (int i = 0; i < fileLength - offset; i++)
						dst[i] = inputBuffer.get(offset + i);
				}
				// 将得到的3M内容给Scanner，这里的XXX是指Scanner解析的分隔符
				Scanner scan = new Scanner(new ByteArrayInputStream(dst)).useDelimiter(" ");
				while (scan.hasNext()) {
					// 这里为对读取文本解析的方法
//					System.out.print(scan.next() + " ");
				}
				scan.close();
			}
			System.out.println("MappedByteBuffer read cost time:"+(System.currentTimeMillis()-start));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		FileReadTest4 sp = new FileReadTest4();
		sp.readResource();
	}
}
