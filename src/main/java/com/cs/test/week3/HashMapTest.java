package com.cs.test.week3;

import java.util.HashMap;
import java.util.Map.Entry;

public class HashMapTest {
	public static void main(String[] args) {
		HashMap<Teacher, String> hashmap = new HashMap<>();
		hashmap.put(new Teacher("zhangsan", 2),"zhangsan");
		hashmap.put(new Teacher("lisi", 1),"lisi");
		hashmap.put(new Teacher("lisi", 1),"lisi2");
		hashmap.put(new Teacher("wangmazi", 3),"wangmazi");
		hashmap.put(new Teacher("mazi", 3),"mazi");
		hashmap.put(new Teacher("madai", 4),"mazi");
		hashmap.put(new Teacher("赵云", 5),"zhaoyun");
		hashmap.put(new Teacher("典韦", 6),"dianwei");
		hashmap.put(new Teacher("周瑜", 7),"zhouyu");
		hashmap.put(new Teacher("孙坚", 8),"sunjian");
		hashmap.put(new Teacher("孙策", 9),"sunce");
		hashmap.put(new Teacher("孙权", 10),"sunquan");
		hashmap.put(new Teacher("孙尚香", 11),"sunshangxiang");
		hashmap.put(new Teacher("雇雍", 12),"guyong");
		hashmap.put(new Teacher("张昭", 13),"zhangzhao");
		hashmap.put(new Teacher("刘备", 14),"liubei");
		hashmap.put(new Teacher("诸葛亮", 15),"zhugeliang");
		hashmap.put(new Teacher("曹操", 16),"caocao");
		hashmap.put(new Teacher("马超", 17),"machao");
		
		for (Entry<Teacher, String> entry : hashmap.entrySet()) {
			System.out.println(entry.getKey() + " ");
		}
	}
}
