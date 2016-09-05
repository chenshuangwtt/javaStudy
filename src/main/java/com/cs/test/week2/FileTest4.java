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

public class FileTest4 {
	public static void main(String[] args) throws Exception {
		try {
			
			List<String> dataList = new ArrayList<>();
			for (int i = 0; i < 10000000; i++) {
				dataList.add(new Salary().write());
			}
			Path path = Paths.get("D:\\salary.txt");
			Files.write(path, dataList, Charset.defaultCharset(), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
//			System.err.println(" data into file time cost ：" + (System.currentTimeMillis() - start));

			long start = System.currentTimeMillis();
			Map<String, Item> resultMap = new TreeMap<String, Item>();
			Files.readAllLines(path).parallelStream().forEach(p -> {
				String[] tempArr = p.split(",");
				if (resultMap.containsKey(tempArr[0].substring(0, 2))) {
					Item item = resultMap.get(tempArr[0].substring(0, 2));
					item.setSum(item.getSum() + Integer.parseInt(tempArr[1]));
					item.setCount(item.getCount() + 1);
				} else {
					Item item = new Item(Integer.parseInt(tempArr[1]), Integer.parseInt(tempArr[2]));
					resultMap.put(tempArr[0].substring(0, 2), item);
				}
			});

			// 这里将map.entrySet()转换成list
			List<Map.Entry<String, Item>> list = new ArrayList<Map.Entry<String, Item>>(resultMap.entrySet());
			// 然后通过比较器来实现排序
			Collections.sort(list, new Comparator<Map.Entry<String, Item>>() {
				// 升序排序
				public int compare(Entry<String, Item> o1, Entry<String, Item> o2) {
					return o1.getValue().getSum() > o2.getValue().getSum() ? -1 : 1;
				}
			});
			int count = 0;
			for (Map.Entry<String, Item> mapping : list) {
				System.out.println(mapping.getKey() + ":" + mapping.getValue().toString());
				count++;
			}
			System.err.println(count);
			System.err.println("共耗时：" + (System.currentTimeMillis() - start));
		} finally {
		}
	}
}
