package com.cs.test.week1;

public class QuickSortTest {
	public static final int ARR_SIZE = 10000;
	public static void main(String[] args) {
//		int a[] = { 20, 3, 5, 18, 90, 100, 30, 21, 8, 19,20 };
		Salary[] arr = genetorData();
		long currentTime = System.currentTimeMillis();
		quickSort(arr, 0, ARR_SIZE-1);
		System.out.println("快排排序总耗时:" + (System.currentTimeMillis() - currentTime));
		for (int i = 0; i < 100; i++) {
			System.out.println(arr[i]); // 打印前10
		}
//		quickSort(a, 0, a.length - 1); // 指定从数组的哪个位置到哪个位置进行排序
		for (int i = 0; i < 100; i++) {
			System.out.println(arr[i].print());
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
