package com.cs.test.week3;

import java.util.IdentityHashMap;

public class IdentityHashMapTest {
	public static void main(String[] args) {
		IdentityHashMap<Integer, String> map = new IdentityHashMap<>();
		Integer a = 5;
		Integer b = 5;
		System.err.println(Integer.valueOf(a)== Integer.valueOf(b));
		map.put(a, "100");
		map.put(b, "100");
		System.out.println(map.size());
		map.clear();
		a = Integer.MAX_VALUE - 1;
		b = Integer.MAX_VALUE - 1;
		System.err.println(Integer.valueOf(a)== Integer.valueOf(b));
		map.put(a, "100");
		map.put(b, "100");
		System.out.println(map.size());
		
	}
}
