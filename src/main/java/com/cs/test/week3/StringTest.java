package com.cs.test.week3;

public class StringTest {
	public static void main(String[] args) {
		// s1,s2分别位于堆中不同空间
		String s1 = new String("hello");
		String s2 = new String("hello");
		System.out.println(s1 == s2);// 输出false
		// s3,s4位于池中同一空间
		String s3 = "hello";
		String s4 = "hello";
		System.out.println(s3 == s4);// 输出true
	}
}