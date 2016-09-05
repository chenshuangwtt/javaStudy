package com.cs.test.week2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class FileGroupSort {
	public static void main(String[] args) {
		FileGroupSort app = new FileGroupSort();
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		long start = System.currentTimeMillis();
		CountTask task = app.new CountTask(1, 100000);
		Future<HashMap<String, Item>> result = forkJoinPool.submit(task);
		try {
			// 这里将map.entrySet()转换成list
			List<Map.Entry<String, Item>> list = new ArrayList<Map.Entry<String, Item>>(result.get().entrySet());
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
			System.err.println("time cost "+(System.currentTimeMillis()-start));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private class CountTask extends RecursiveTask<HashMap<String, Item>> {
		private static final int THRESHOLD = 50000;
		private int start;
		private int end;

		public CountTask(int start, int end) {
			this.start = start;
			this.end = end;
		}

		protected HashMap<String, Item> compute() {

			HashMap<String, Item> temp = new HashMap<>();

			if ((end - start) <= THRESHOLD) {
				temp = readSort(start, end);
			} else {
				int middle = (start + end) / 2;
				CountTask leftTask = new CountTask(start, middle);
				CountTask rightTask = new CountTask(middle + 1, end);
				leftTask.fork();
				rightTask.fork();

				HashMap<String, Item> leftResult = leftTask.join();
				HashMap<String, Item> rightResult = rightTask.join();

				temp = addTo(leftResult, rightResult);
			}
			return temp;
		}

		private HashMap<String, Item> readSort(int start, int end) {
			HashMap<String, Item> resultMap = new HashMap<String, Item>();
			RandomAccessFile  rdf = null;
			try {
				File f = new File("d:" + File.separator + "temp.txt"); // 指定要操作的文件
				rdf = new RandomAccessFile(f, "r");
				rdf.skipBytes(14*start);
				String line = "";
				int i = start;
				while ((line = rdf.readLine()) != null) {
					i++;
					if (i<=end) {
						String[] tempArr = line.split(",");
						if (resultMap.containsKey(tempArr[0].substring(0, 2))) {
							Item item = resultMap.get(tempArr[0].substring(0, 2));
							item.setSum(
									item.getSum() + Integer.parseInt(tempArr[1]) * 13 + Integer.parseInt(tempArr[2])); // 计算年薪
							item.setCount(item.getCount() + 1);
						} else {
							Item item = new Item(Integer.parseInt(tempArr[1]) * 13 + Integer.parseInt(tempArr[2]), 1);// 初始化一个item
							resultMap.put(tempArr[0].substring(0, 2), item);
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					rdf.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return resultMap;
		}

		public HashMap<String, Item> addTo(HashMap<String, Item> target, HashMap<String, Item> plus) {
			Object[] os = plus.keySet().toArray();
			String key;
			for (int i = 0; i < os.length; i++) {
				key = (String) os[i];
				if (target.containsKey(key))
					target.put(key, target.get(key).add(plus.get(key)));
				else
					target.put(key, plus.get(key));
			}
			return target;
		}
	}

}