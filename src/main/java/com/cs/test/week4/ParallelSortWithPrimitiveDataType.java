package com.cs.test.week4;

import java.util.Arrays;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;

public class ParallelSortWithPrimitiveDataType {
	public static void main(String[] args) {
		int[] num1 = { 3, 6, 2, 10, 4, 1, 7 };
		System.out.println("--Sort complete Integer array--");
		Arrays.parallelSort(num1);
		IntConsumer printInt = i -> System.out.print(i + " ");
		Arrays.stream(num1).forEach(printInt);
		System.out.println("\n--Sort Integer array from index 1 to 5--");
		int[] num2 = { 3, 6, 2, 10, 4, 1, 7 };
		Arrays.parallelSort(num2, 1, 5);		//排序区间的理解  下标1开始 到下标5之前结束
		Arrays.stream(num2).forEach(printInt);

		double[] db1 = { 3.5, 1.2, 6.7, 8.9, 0.6, 2.3, 5.5 };
		System.out.println("\n--Sort complete Double array--");
		Arrays.parallelSort(db1);
		DoubleConsumer printDB = d -> System.out.print(d + " ");
		Arrays.stream(db1).forEach(printDB);
		System.out.println("\n--Sort Double array from index 1 to 5--");
		double[] db2 = { 3.5, 1.2, 6.7, 8.9, 0.6, 2.3, 5.5 };
		Arrays.parallelSort(db2, 1, 5);
		Arrays.stream(db2).forEach(printDB);

	}
}
