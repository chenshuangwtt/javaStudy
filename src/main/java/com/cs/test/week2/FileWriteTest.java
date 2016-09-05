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
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

public class FileWriteTest {
	public static void main(String[] args) throws Exception {
		try {
			long start = System.currentTimeMillis();
			List<String> dataList = new ArrayList<>();
			for (int i = 0; i < 10000000; i++) {
				dataList.add(new Salary().write());
			}
			Path path = Paths.get("D:\\temp.txt");
			Files.write(path, dataList, Charset.forName("UTF-8"),
					StandardOpenOption.WRITE, StandardOpenOption.CREATE);
//			dataList.stream().
			System.err.println(" data into file time cost ：" + 
					(System.currentTimeMillis() - start));
		} finally {
		}
	}
}
