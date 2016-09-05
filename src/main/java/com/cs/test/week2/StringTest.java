package com.cs.test.week2;

import java.io.UnsupportedEncodingException;

public class StringTest {
	public static void main(String[] args) {
		try {
			String s = new  String("中国".getBytes(),"GBK");
			System.out.println("GBK字节长度:"+new  String("中国".getBytes(),"GBK").getBytes().length);
			System.out.println("UTF-8字节长度:"+new  String("中国".getBytes(),"UTF-8").getBytes().length);
			System.out.println("iso-8859-1字节长度:"+new  String("中国".getBytes(),"iso-8859-1").getBytes().length);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
