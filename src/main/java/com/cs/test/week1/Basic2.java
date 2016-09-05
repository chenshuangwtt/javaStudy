package com.cs.test.week1;

import java.util.Random;

/**
 * 行优先和列优先两种。具体情况如下： 数组的顺序存储方式 由于计算机内存是一维的，多维数组的元素应排成线性序列后存人存储器。
 * 数组一般不做插入和删除操作，即结构中元素个数和元素间关系不变化。一般采用顺序存储方法表示数组。 
 * （1）行优先顺序
 * 将数组元素按行向量排列，第i+1个行向量紧接在第i个行向量后面。 
 * 【例】二维数组Amn的按行优先存储的线性序列为：
 * a11,a12,…,a1n,a21,a22,…,a2n,……，am1,am2,…，amn 行优先顺序推广到多维数组，可规定为先排最右的下标。
 * （2）列优先顺序 将数组元素按列向量排列，第i+1个列向量紧接在第i个列向量后面。 
 * 【例】二维数组Amn的按列优先存储的线性序列为：
 * a11,a21,…,am1,a12,a22,…,am2,……，a1n,a2n,…，amn 列优先顺序推广到多维数组，可规定为先排最左的下标。
 *
 */

public class Basic2 {
	private static final int ARR_SIZE = 10240;
	//生成数组
	public static Byte[][] getRandombyte() {
		Random random = new Random();
		Byte[][] bytes = new Byte[ARR_SIZE][ARR_SIZE];
		for (int i = 0; i < ARR_SIZE; i++) {
			Byte[] temp = new Byte[ARR_SIZE];
			for (int j = 0; j < ARR_SIZE; j++) {
				Integer is = random.nextInt(10);
				temp[j] = Byte.parseByte(is.toString());
			}
			bytes[i] = temp;
		}
		return bytes;
	}

	public static void main(String[] args) {
		Byte arr[][] = getRandombyte();   // 新数组
		for (int i = 0; i < 100; i++) {
			if (i == 99) {
				long currTime = System.currentTimeMillis();
				System.out.println(rowMajor(arr));
				System.out.println("行优先 :" + (System.currentTimeMillis() - currTime) + " ms");
				currTime = System.currentTimeMillis();
				System.out.println(colMajor(arr)); 
				System.out.println("列优先：" + (System.currentTimeMillis() - currTime) + " ms");
			} else {
				colMajor(arr);
				rowMajor(arr);
			}
		}
	}
	/**
	 * 下面的代码按列优先遍历数组 即在扫描下一列之前先扫描完本列
	 */
	private static int colMajor(Byte arr[][]) {
		int sum = 0;
		for (int i = 0; i < ARR_SIZE; i++) {
			for (int j = 0; j < ARR_SIZE; j++) {
				//我们先遍历j，然后遍历i，但是对于访问元素来说 它们在更远的位置，所以需要花费的更多
				sum += arr[j][i];
			}
		}
		return sum;
	}

	/**
	 * 如果我们转换内外循环 程序就以行优先顺序遍历数组 即在扫描下一行之前先扫描完本行
	 */
	private static int rowMajor(Byte arr[][]) {
		int sum = 0;
		for (int i = 0; i < ARR_SIZE; i++) {
			for (int j = 0; j < ARR_SIZE; j++) {
				sum += arr[i][j];
			}
		}
		return sum;
	}
}
