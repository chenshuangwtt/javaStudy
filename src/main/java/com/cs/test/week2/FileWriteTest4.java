package com.cs.test.week2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FileWriteTest4 {
	public static void main(String[] args) throws IOException {
		File file = new File("D:", "temp.txt");
		file.createNewFile(); // 创建文件
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		FileChannel fileChannel = fileOutputStream.getChannel();
		long start = System.currentTimeMillis();
		for (int i = 0; i < 10000000; i++) {
			fileChannel.write(ByteBuffer.wrap(
					(new Salary().write()+"\n").getBytes("UTF-8")));
		}
		fileChannel.close();
		fileOutputStream.close();
		System.err.println("共耗时："+(System.currentTimeMillis()- start));	
	}
}
