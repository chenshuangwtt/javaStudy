package com.cs.test.week7.tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.BrokenBarrierException;
import java.util.Map.Entry;

import com.cs.test.week2.Item;

public class Main {

	public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
		Map<String, Item> resultMap = new TreeMap<String, Item>();
		long start = System.currentTimeMillis();
		BigFileReader.Builder builder = new BigFileReader.Builder("d:/salary.txt", new IHandle() {
			@Override
			public void handle(String line) {
				String[] tempArr = line.split(",");
				if (resultMap.containsKey(tempArr[0].substring(0, 2))) {
					Item item = resultMap.get(tempArr[0].substring(0, 2));
					item.setSum(item.getSum() + Integer.parseInt(tempArr[1]) * 13 
							+ Integer.parseInt(tempArr[2])); // 计算年薪
					item.setCount(item.getCount() + 1);
				} else {
					Item item = new Item(Integer.parseInt(tempArr[1]) * 13 
							+ Integer.parseInt(tempArr[2]), 1);// 初始化一个item
					resultMap.put(tempArr[0].substring(0, 2), item);
				}
			}
		});
		builder.withTreahdSize(8).withCharset("UTF-8").withBufferSize(1024 * 1024);
		BigFileReader bigFileReader = builder.build();
		bigFileReader.start();
		System.out.println(".........");
		bigFileReader.getCyclicBarrier().await();
		System.out.println("+++++++++");
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
		System.out.println("cost time " 
				+ (System.currentTimeMillis() - start));
	}
}