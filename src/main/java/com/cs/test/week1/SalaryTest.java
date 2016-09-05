package com.cs.test.week1;

import java.util.Arrays;
public class SalaryTest {
	public static void main(String[] args) {
		Salary []  arr = new Salary[10000];
		for (int i = 0; i < 10000; i++) {
			arr[i] = new Salary();
		}
		Arrays.sort(arr);	//排序
		for (int i = 0; i < 10; i++) {
			System.out.println(arr[i]);	//打印前10
		}
	}
}
