package com.cs.test.week6;

import java.util.List;
import java.util.Vector;

public class Pianxiang {
	public static List<Integer> numberList = new Vector<Integer>();
	public static void main(String[] args) {
		long begin = System.currentTimeMillis();
		int count = 0;
		int startnum = 0;
		while (count < 10000000) {
			numberList.add(startnum);
			startnum += 2;
			count++;
		}
		long end = System.currentTimeMillis();
		System.out.println(end - begin);
	}
}