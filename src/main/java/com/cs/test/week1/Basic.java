package com.cs.test.week1;

public class Basic {
	public static void main(String[] args) {
		/**
		 * 一个btye类型占8个位，按照2的八次方来乘积实际等于256，
		 * 但因为第一位表示的正数还是负数，所以实际是2的7次方来算，所以是127到-128
		 * 左移位：<<，有符号的移位操作左移操作时将运算数的二进制码整体左移指定位数，左移之后的空位用0补充
		 * 
		 */
		// byte ba = 127;
		// byte bb = ba << 2;
		// System.out.println(bb);
		// int num = -1024;
		// printInfo(num);
		// printInfo(1024);
		int a = 1024;
		for (int i = 0; i < 32; i++) {
			int t = (a & 0x80000000 >>> i) >>> (31 - i);
			System.out.print(t);
		}
	}

}
