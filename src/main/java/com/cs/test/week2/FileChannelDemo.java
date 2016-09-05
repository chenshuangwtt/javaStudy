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

public class FileChannelDemo {
	public static void main(String[] args) throws IOException {
		File file = new File("D:", "salary.txt");
		file.createNewFile(); // 创建文件
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		FileChannel fileChannel = fileOutputStream.getChannel();
		long start = System.currentTimeMillis();
		for (int i = 0; i < 10000000; i++) {
			fileChannel.write(ByteBuffer.wrap((new Salary().write()+"\n").getBytes("UTF-8")));
		}
		fileChannel.close();
		fileOutputStream.close();
		System.err.println("共耗时："+(System.currentTimeMillis()- start));
		
		
//		FileInputStream fileInputStream = new FileInputStream(file);
//		fileChannel = fileInputStream.getChannel();
//		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
//	    while (fileChannel.read(byteBuffer) != -1) {
//            fileChannel.read(byteBuffer);
//            byteBuffer.flip();
//            Charset  cs = Charset.defaultCharset();  
//            System.out.println(cs.decode(byteBuffer));
//        }
	}
	
//	private static ArrayList<SalaryAnalysis> write2File(String filename, List<String> bsList) {
//		List<SalaryAnalysis> saList = new ArrayList<SalaryAnalysis>();
//		try {
//
//			File w2f = new File(filename);
//			if (w2f.exists()) {
//				w2f.createNewFile();
//			}
//
//			if (bsList == null) {
//				System.out.println("传入的ArrayList<Salary>为空");
//				return null;
//			}
//
//			if (bsList.size() == 0) {
//				System.out.println("传入的ArrayList<Salary>的size为0");
//				return null;
//			}
//
//			long wbegin = System.currentTimeMillis();
//			System.out.println("-----write--------" + filename + "-----content--------");
//		
//			String contentPath = w2f.getCanonicalPath();
//			System.out.println("write uri is " + contentPath);
//						
//			Path path = Paths.get(contentPath);			
//			Files.write(path, bsList, Charset.defaultCharset(), StandardOpenOption.WRITE, StandardOpenOption.CREATE); 
//			
//			long wend = System.currentTimeMillis();
//			long wdiff = wend - wbegin;
//			System.out.println("write data to file diff is " + wdiff);
//
//		} catch (Exception e) {
//			System.err.println("error for write2File："+e);
//		}
//
//		return (ArrayList<SalaryAnalysis>) saList;
//	}
	
}
