package com.cs.test.week1;

import java.util.Arrays;
import java.util.Random;

public class ByteStore {
	public static byte[] storeByteArry = new byte[1000];
	public static int[] storeIntArray = new int[1000];

	//将int数组中数字转为对象
	public static MyItem convertToItem(int index) {
		byte[] bytes = new byte[3];
		int item = storeIntArray[index];
		bytes[0] = (byte) ((item>>16) & 0xFF);
		bytes[1] = (byte) ((item>> 8) & 0xFF);
		bytes[2] = (byte) (item & 0xFF);
		return new MyItem(bytes);
	}

	//方式对象到int数组中
	public static void putItemToIntArr(int index,MyItem item){
		System.out.println(item);
		storeIntArray[index] = item.toByte();
	}
	
	
	// 在指定的Index上存放MyItem的属性，这里的Index是0-999，而不是storeByteArry的Index
	public static void putMyItem(int index, MyItem item) {
		try {
			if (index < 0 || index > 999) {
				throw new Exception("输入超过范围");
			}
			ByteStore.storeByteArry[3 * index] = item.getType();
			ByteStore.storeByteArry[3 * index + 1] = item.getColor();
			ByteStore.storeByteArry[3 * index + 2] = item.getPrice();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 从指定的Index上查找MyItem的属性，并返回对应的MyItem对象。
	public static MyItem getMyItem(int index) {
		byte[] store = Arrays.copyOfRange(storeByteArry, index, index + 3);
		return new MyItem(store);
	}

	
	
	public static void main(String[] args) {
		MyItem item1 = new MyItem((byte) 1, (byte) 1, (byte) 1);
		MyItem item2 = new MyItem((byte) 2, (byte) 2, (byte) 2);
		MyItem item3 = new MyItem((byte) 3, (byte) 3, (byte) 3);

		ByteStore.putMyItem(0, item1);
		ByteStore.putMyItem(1, item2);
		ByteStore.putMyItem(2, item3);
		// 在getItem中最后是重新New对象了
		System.out.println(ByteStore.getMyItem(0).equals(item1));
		System.out.println(ByteStore.getMyItem(1).equals(item2));
		System.out.println(ByteStore.getMyItem(2).equals(item3));
	}
}
