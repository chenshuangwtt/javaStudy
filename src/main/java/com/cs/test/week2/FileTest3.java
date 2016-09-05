package com.cs.test.week2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;

public class FileTest3 {
	public static void main(String[] args) throws Exception {
		BufferedWriter bufferedWriter = null;
		try {
			long start = System.currentTimeMillis();
			File file = new File("D:", "salary.txt");
			file.createNewFile(); // 创建文件
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream,"UTF-8"));
			for (int i = 0; i < 10000; i++) {
				bufferedWriter.write((new Salary().write()+"\n"));
			}
			System.err.println("共耗时："+(System.currentTimeMillis()- start));
		} finally {
			bufferedWriter.close();
		}
	}
}
