package com.cs.test.week2;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Map.Entry;

public class MapMemeryBuffer {
	public static void main(String[] args) throws Exception {
		long start = System.currentTimeMillis();
		readResource();
		System.err.println("共耗时：" + (System.currentTimeMillis() - start));
	}

	public static void readResource() throws IOException {
		long fileLength = 0;
		final int BUFFER_SIZE = 14*1000; //0x300000;// 3M的缓冲

		// for (String fileDirectory : this.readResourceDirectory())//
		// 得到文件存放路径，我这里使用了一个方法从XML文件中读出文件的
		// 存放路径，当然也可以用绝对路径来代替这里的fileDriectory
		// {
		File file = new File("D:", "salary2.txt");
		file.createNewFile(); // 创建文件
		FileOutputStream out = new FileOutputStream(file);
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8")); 
		for (int i = 0; i < 10000000; i++) {
			writer.write(new Salary2().writeFull()+"\n");
		}
		writer.close();
		
		fileLength = file.length();
		try {
//			MappedByteBuffer inputBuffer = new RandomAccessFile(file, "r").getChannel()
//					.map(FileChannel.MapMode.READ_ONLY, 0, fileLength);// 读取大文件
//			
//			byte[] dst = new byte[BUFFER_SIZE];// 每次读出内容
//
//			Map<String, Item> resultMap = new TreeMap<String, Item>();
//			for (int offset = 0; offset < fileLength; offset += BUFFER_SIZE) {
//				if (fileLength - offset >= BUFFER_SIZE) {
//					for (int i = 0; i < BUFFER_SIZE; i++)
//						dst[i] = inputBuffer.get(offset + i);
//				} else {
//					for (int i = 0; i < fileLength - offset; i++)
//						dst[i] = inputBuffer.get(offset + i);
//				}
//				// 将得到的3M内容给Scanner，这里的XXX是指Scanner解析的分隔符
//				Scanner scan = new Scanner(new ByteArrayInputStream(dst)).useDelimiter("\n");
//				while (scan.hasNext()) {
//					// 这里为对读取文本解析的方法
//					String temp = scan.next();
////					System.out.println(temp);
//						String[] tempArr = temp.split(",");
//						if (resultMap.containsKey(tempArr[0].substring(0, 2))) {
//							Item item = resultMap.get(tempArr[0].substring(0, 2));
//							item.setSum(item.getSum() + Integer.parseInt(tempArr[1])*13+Integer.parseInt(tempArr[2]));  //计算年薪
//							item.setCount(item.getCount() + 1);
//						} else {
//							Item item = new Item(Integer.parseInt(tempArr[1])*13+Integer.parseInt(tempArr[2]),1);//初始化一个item 
//							resultMap.put(tempArr[0].substring(0, 2), item);
//						}
//					}
//				scan.close();
//			}
//			// 这里将map.entrySet()转换成list
//			List<Map.Entry<String, Item>> list = new ArrayList<Map.Entry<String, Item>>(resultMap.entrySet());
//			// 然后通过比较器来实现排序
//			Collections.sort(list, new Comparator<Map.Entry<String, Item>>() {
//				// 升序排序
//				public int compare(Entry<String, Item> o1, Entry<String, Item> o2) {
//					return o1.getValue().getSum() > o2.getValue().getSum() ? -1 : 1;
//				}
//			});
//			int count = 0;
//			for (Map.Entry<String, Item> mapping : list) {
//				System.out.println(mapping.getKey() + ":" + mapping.getValue().toString());
//				count++;
//			}
//			System.err.println(count);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
