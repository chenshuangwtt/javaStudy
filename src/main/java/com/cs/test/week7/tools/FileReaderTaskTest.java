package com.cs.test.week7.tools;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

public class FileReaderTaskTest {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		File file = new File("d:\\salary.txt");
		FileReaderTask fileReaderTask = new FileReaderTask(file,"UTF-8",1024*1024);
		forkJoinPool.invoke(fileReaderTask);
		ConcurrentHashMap<String, Item> resultMap = fileReaderTask.get();
//		System.out.println(fileReaderTask.get());
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
			System.out.println(mapping.getKey() 
					+ ":" + mapping.getValue().toString());
			count++;
			if (count == 10) {
				break;
			}
		}
	}
}
