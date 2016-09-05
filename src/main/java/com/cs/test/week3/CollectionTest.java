package com.cs.test.week3;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class CollectionTest {
	static final int size = 1000000;
	public static void main(String[] args) {
		Map<String, String> hashmap = new HashMap<String, String>();
		Map<String, String> linkmap = new LinkedHashMap<String, String>();
		for (int i = 0; i < size; i++) {
			hashmap.put("" + i, "" + i);
		}
		long start = System.currentTimeMillis();
		System.out.println("HashMap遍历输出：");
		int j = 0;
		for (Entry<String, String> entry : hashmap.entrySet()) {
			if (j < 10) {
				System.out.print(entry.getKey() + " ");
			}
			j++;
		}
		System.err.println("hashmap cost time " + (System.currentTimeMillis() - start));
		start = System.currentTimeMillis();
		System.out.println("");
		for (int i = 0; i < size; i++) {
			linkmap.put("" + i, "" + i);
		}
		System.out.println("LinkedHashMap遍历输出：");
		j = 0;
		for (Entry<String, String> entry : linkmap.entrySet()) {
			if (j < 10) {
				System.out.print(entry.getKey() + " ");
			}
			j++;
		}
		System.out.println("");
		System.err.println("linkedhashmap cost time " + (System.currentTimeMillis() - start));
	}
}
