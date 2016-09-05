package com.cs.test.week1;

public class QuickSort {
	public static final int ARR_SIZE = 10000;

	public static void main(String[] args) {
		Salary[] arr = genetorData();
		long currentTime = System.currentTimeMillis();
		quickSort(arr, 0, ARR_SIZE-1);
		System.out.println("冒泡排序总耗时:" + (System.currentTimeMillis() - currentTime));
		for (int i = 0; i < 100; i++) {
			System.out.println(arr[i]); // 打印前10
		}
	}

	public static Salary[] genetorData() {
		Salary[] arr = new Salary[ARR_SIZE];
		for (int i = 0; i < ARR_SIZE; i++) {
			arr[i] = new Salary();
		}
		return arr;
	}

	public static void quickSort(Salary a[], int start, int end) {
		int i, j;
		i = start;
		j = end;
		if ((a == null) || (a.length == 0))
			return;

		while (i < j) {// 查找基准点下标
			while (i < j && a[i].totalSalary() <= a[j].totalSalary())
				// 以数组start下标的数据为key，右侧扫描
				j--;
			if (i < j) { // 右侧扫描，找出第一个比key小的，交换位置
				Salary temp = a[i];
				a[i] = a[j];
				a[j] = temp;
			}
			while (i < j && a[i].totalSalary() < a[j].totalSalary())
				// 左侧扫描（此时a[j]中存储着key值）
				i++;
			if (i < j) { // 找出第一个比key大的，交换位置
				Salary temp = a[i];
				a[i] = a[j];
				a[j] = temp;
			}
		}
		if (i - start > 1) { // 递归调用，把key前面的完成排序
			quickSort(a, 0, i - 1);
		}
		if (end - j > 1) {
			quickSort(a, j + 1, end); // 递归调用，把key后面的完成排序
		}
	}
}
