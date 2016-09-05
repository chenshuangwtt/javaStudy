package com.cs.test.week1;

import java.util.Arrays;
import java.util.Random;

public class MyItemSort {
	public static void main(String[] args) {
//		MyItem[] arr = new MyItem[1000];
		Random random = new Random();
//		for (int i = 0; i < arr.length; i++) {
//			arr[i] = new MyItem(
//					new byte[] { (byte) random.nextInt(), (byte) random.nextInt(), (byte) random.nextInt() });
//		}
//		Arrays.parallelSort(arr);
//		for(int i=0;i<100;i++){
//			System.out.println(arr[i]);
//		}
		
		//int数组测试
		for (int i = 0; i < ByteStore.storeIntArray.length; i++) {
			ByteStore.storeIntArray[i] = new MyItem(
					new byte[] { (byte) random.nextInt(), 
							(byte) random.nextInt(), (byte) random.nextInt()}).toByte();
		}
		
		for(int i=0;i<10;i++){
			System.err.println(ByteStore.convertToItem(i));
		}
	}
}
