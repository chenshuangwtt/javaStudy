package com.cs.test.week1;

public class SortTest {
	public static final int ARR_SIZE=10000;
	public static void main(String[] args) throws Exception {
		Salary[] arr = genetorData();
		long currentTime = System.currentTimeMillis();
		// 冒泡排序
		bubbleSort(arr);
		System.out.println("冒泡排序总耗时:" + (System.currentTimeMillis() - currentTime));
		Salary[] arr2 = genetorData();
		//快速排序
		currentTime = System.currentTimeMillis();
		quickSort(arr2, 0, ARR_SIZE-1);
		System.out.println("快速排序总耗时:" + (System.currentTimeMillis() - currentTime));
		for (int i = 0; i < 100; i++) {
			System.out.println(arr2[i].print()); // 打印前100
		}
	}
	//生成数据
	public static Salary[] genetorData(){
		Salary[] arr = new Salary[ARR_SIZE];
		for (int i = 0; i < ARR_SIZE; i++) {
			arr[i] = new Salary();
		}
		return arr;
	}
	// 冒泡排序
	private static void bubbleSort(Salary[] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length - i - 1; j++) {
				if (arr[j].totalSalary() < arr[j + 1].totalSalary()) {
					Salary temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
	}
	// 快速排序
	public static void quickSort(Salary a[], int start, int end) {
		int temp = start;
		int i = start;
		int j = end;
		while (i != j) {
			if (temp == i) {
				for (; j > i; j--) {
					if (a[temp].totalSalary() < a[j].totalSalary()) {
						swap(a, temp, j);
						temp = j;
						i++;
						break;
					}
				}
			}
			if (temp == j) {
				for (; i < j; i++) {
					if (a[temp].totalSalary() > a[i].totalSalary()) {
						swap(a, temp, i);
						temp = i;
						j--;
						break;
					}
				}
			}
		}
		/*
		 * 左、右两边重新递归调用该排序算法，请注意if语句里面的条件判断，
		 * 是start<temp-1而不是start<temp，如果写后面的就会报栈溢出的错误
		 */
		if (start < temp - 1) {
			quickSort(a, start, temp - 1);
		}
		if (temp + 1 < end) {
			quickSort(a, temp + 1, end);
		}
	}
	/*
	 * 将数组中的两个数进行交换
	 */
	private static void swap(Salary[] a, int t, int e) {
		Salary temp = a[t];
		a[t] = a[e];
		a[e] = temp;
	}
}
