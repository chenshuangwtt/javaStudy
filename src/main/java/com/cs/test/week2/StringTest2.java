package com.cs.test.week2;

import java.io.UnsupportedEncodingException;

/**
 *  
 */
public class StringTest2 {
	public static void main(String[] args) {
		try {
			String str ="中国";
			byte[] utfBytes = str.getBytes("UTF-8");
			System.out.println(new String(utfBytes,"GBK"));
			String s = new String(str.getBytes("UTF-8"),"GBK");
			System.err.println(s);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
