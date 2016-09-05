package com.cs.test.week3;

public class Test {
	public static void main(String[] args) {
		Integer i1 = new Integer(1);
		Integer i2 = new Integer(1);
		// i1,i2分别位于堆中不同的内存空间
		System.out.println(i1 == i2);// 输出false
		Integer i3 = 1;
		Integer i4 = 1;
		// i3,i4指向常量池中同一个内存空间
		System.out.println(i3 == i4);// 输出true
		// 很显然，i1,i3位于不同的内存空间
		System.out.println(i1 == i3);// 输出false
	}
}