package com.cs.test.week3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public class ArraysTest {
	public static void main(String[] args) {
		//声明数组
		String[] aArray = new String[5];  
		String[] bArray = {"a","b","c", "d", "e"};  
		String[] cArray = new String[]{"a","b","c","d","e"};  
		//1.打印数组
		int[] intArray = { 1, 2, 3, 4, 5 };  
		String intArrayString = Arrays.toString(intArray);  
		// 直接打印,则会打印出引用对象的Hash值  
		System.out.println(intArray);  
		// [1, 2, 3, 4, 5]  
		System.out.println(intArrayString);
		//2. 根据数组创建ArrayList
		String[] stringArray = { "a", "b", "c", "d", "e" };  
		ArrayList<String> arrayList =
				new ArrayList<String>(Arrays.asList(stringArray));  
		// [a, b, c, d, e]  
		System.out.println(arrayList);  
		//3. 检查数组是否包含某个值
		boolean b = Arrays.asList(stringArray).contains("a");  
		// true  
		System.out.println(b);  
		
		//4. 合并连接两个数组
		int[] intArray1 = { 1, 2, 3, 4, 5 };  
		int[] intArray2 = { 6, 7, 8, 9, 10 };  
		// Apache Commons Lang 库  
		int[] combinedIntArray = ArrayUtils.addAll(intArray1, intArray2);  
		
		//5.声明内联数组
		//method(new String[]{"a", "b", "c", "d", "e"});
		//6. 用给定的字符串连结(join)数组
		// containing the provided list of elements  
		// Apache common lang  
		String j = StringUtils.join(new String[] { "a", "b", "c" }, ", ");  
		// a, b, c  
		System.out.println(j);  
		
		//7. 将ArrayList转换为数组
		ArrayList<String> arrayList7 = new ArrayList<String>(Arrays.asList(stringArray));  
		String[] stringArr = new String[arrayList7.size()];  
		arrayList7.toArray(stringArr);  
		for (String s : stringArr)  
		    System.out.println(s);  
		
		//8. 将数组转换为Set
		Set<String> set = new HashSet<String>(Arrays.asList(stringArray));  
		//[d, e, b, c, a]  
		System.out.println(set);  
		
		//9. 数组元素反转
		int[] intArray9 = { 1, 2, 3, 4, 5 };  
		ArrayUtils.reverse(intArray9);  
		//[5, 4, 3, 2, 1]  
		System.out.println(Arrays.toString(intArray9));  
		//10. 移除元素
		int[] intArray10 = { 1, 2, 3, 4, 5 };  
		int[] removed = ArrayUtils.removeElement(intArray10, 3);//创建新的数组  
		System.out.println(Arrays.toString(removed));  
	}
}
