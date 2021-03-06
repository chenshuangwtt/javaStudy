package com.cs.test.week3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ArrayConvertCollection {
	public static void main(String[] args) {
		list2Array();
		array2List();
		test1();
	}

	/**
	 * 数组转集合
	 */
	public static void list2Array() {
		System.out.println("数组转集合:");
		String[] str = new String[] { "1", "2", "3" };
		List list = Arrays.asList(str);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

	/**
	 * 集合转数组
	 */
	public static void array2List() {
		System.out.println("集合转数组:");
		List list = new ArrayList();
		list.add(1);
		list.add(2);
		list.add(3);
		Integer[] in = new Integer[list.size()];
		in = (Integer[]) list.toArray(in);
		for (int k = 0; k < in.length; k++) {
			System.out.println(in[k]);
		}
	}
	
	
	/**
	 * 转换
	 */
	
	public static void test1(){
		List list = new ArrayList();  
        list.add("a");  
        list.add("b");  
        list.add("c");  
        list.add("d");  
        // list.add(1);//会产生java.lang.ArrayStoreException异常  
        // 当list中的数据类型都一致时可以将list转化为数组  
        Object[] array = list.toArray();  
        System.out.println("从list转换成的对象数组长度为：" + array.length);  
        // 在转化为其它类型的数组时需要强制类型转换，并且，要使用带参数的toArray方法，参数为对象数组，  
        // 将list中的内容放入参数数组中，当参数数组的长度小于list的元素个数时，会自动扩充数组的长度以适应list的长度  
        String[] array1 = (String[]) list.toArray(new String[0]);  
        System.out.println("从list转换成的字符串数组长度为：" + array1.length);  
        // 分配一个长度与list的长度相等的字符串数组  
        String[] array2 = (String[]) list.toArray(new String[list.size()]);  
        System.out.println("从list转换成的字符串数组长度为：" + array2.length);  
        list.clear();  
        // 将数组转换成list  
        for (int i = 0; i < array.length; i++) {  
            list.add(array[i]);  
        }  
        System.out.println("将数组转换成list的元素个数为：" + list.size());  
        list.clear();  
        // 直接使用Arrays的asList方法  
        list = Arrays.asList(array);  
        System.out.println("将数组转换成list的元素个数为：" + list.size());  
        Set set = new HashSet();  
        set.add("a");  
        set.add("b");  
        // 将set转换为数组  
        array = set.toArray();  
        array1 = (String[]) set.toArray(new String[0]);  
        array2 = (String[]) set.toArray(new String[set.size()]);  
        System.out.println("从Set转换成的对象数组长度为：" + array.length);  
        System.out.println("从Set转换成的字符串数组长度为：" + array2.length);  
        // 数组转换成Set  
        // 将数组转换成List后，再用List构造Set  
        set = new HashSet(Arrays.asList(array));  
        System.out.println("将数组转换成Set的元素个数为：" + set.size());  
        // 将Set清空，然后把数组转换成的list全部add  
        set.clear();  
        set.addAll(Arrays.asList(array1));  
        System.out.println("将数组转换成Set的元素个数为：" + set.size());  
    }  
}
