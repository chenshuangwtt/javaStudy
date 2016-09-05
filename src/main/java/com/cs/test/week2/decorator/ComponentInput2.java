package com.cs.test.week2.decorator;

import java.util.Stack;

/**
 * 字符串反转
 */
public class ComponentInput2 extends Decorator {
	public ComponentInput2(ClientInput clientInput) {
		super(clientInput);
	}
	public String doWriting(String input) {
		input = super.doWriting(input);
		return reverse(input);
	}

	// jdk自带
	public String reverse(String str) {
		return new StringBuilder(str).reverse().toString();
	}
	
	private void swap(char[] arr, int begin, int end) {
		while (begin < end) {
			char temp = arr[begin];
			arr[begin] = arr[end];
			arr[end] = temp;
			begin++;
			end--;
		}
	}

	private String swapWords(String str) {
		char[] arr = str.toCharArray();
		swap(arr, 0, arr.length - 1);
		int begin = 0;
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] == ' ') {
				swap(arr, begin, i - 1);
				begin = i + 1;
			}
		}
		return new String(arr);
	}


	public  String reverse1(String s) {
		int length = s.length();
		if (length <= 1)
			return s;
		String left = s.substring(0, length / 2);
		String right = s.substring(length / 2, length);
		return reverse1(right) + reverse1(left);
	}

	public  String reverse2(String s) {
		int length = s.length();
		String reverse = "";
		for (int i = 0; i < length; i++)
			reverse = s.charAt(i) + reverse;
		return reverse;
	}

	public  String reverse3(String s) {
		char[] array = s.toCharArray();
		String reverse = "";
		for (int i = array.length - 1; i >= 0; i--)
			reverse += array[i];

		return reverse;
	}

	public  String reverse7(String s) {
		char[] str = s.toCharArray();
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < str.length; i++)
			stack.push(str[i]);

		String reversed = "";
		for (int i = 0; i < str.length; i++)
			reversed += stack.pop();

		return reversed;
	}

}
