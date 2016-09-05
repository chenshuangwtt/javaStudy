package com.cs.test.week3;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConCurrentHashMapTest {
	public static int size = 20000000;
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		Map<String, String> linkmap = new ConcurrentHashMap<String, String>();
		for (int i = 0; i < size; i++) {
			linkmap.put("" + i, "" + i);
		}
		System.err.println(System.currentTimeMillis()-start);
		System.out.println(linkmap.size());
	}
}
