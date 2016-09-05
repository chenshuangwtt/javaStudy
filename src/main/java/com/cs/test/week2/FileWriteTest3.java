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

public class FileWriteTest3 {
	public static void main(String[] args) throws Exception {
		BufferedWriter writer  = null;
	try {
		long start = System.currentTimeMillis();
		File file = new File("D:", "temp.txt");
		file.createNewFile(); // 创建文件
		FileOutputStream out = new FileOutputStream(file);
		//设置输出文件编码格式
		writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
		//生成数据
		for (int i = 0; i < 10000000; i++) {
			writer.write(new Salary().write()+"\n");
		}
		writer.close();
		System.err.println("共耗时："+(System.currentTimeMillis()- start));
	} finally {
	}
	}
}
