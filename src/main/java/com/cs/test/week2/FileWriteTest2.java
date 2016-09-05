package com.cs.test.week2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileWriteTest2 {
	public static void main(String[] args) throws Exception {
		PrintStream ps  = null;
		try {
			long start = System.currentTimeMillis();
			File file = new File("D:", "temp.txt");
			file.createNewFile(); // 创建文件
			FileOutputStream out = new FileOutputStream(file);
			ps = new PrintStream(out, true, "UTF-8");
			for (int i = 0; i < 10000000; i++) {
				ps.println(new Salary().write());
			}
			System.err.println("共耗时："+(System.currentTimeMillis()- start));
		} finally {
			ps.close();
		}
	}
}
