package com.cs.test.week3;

import java.util.HashSet;
import java.util.Iterator;

public class HashSetTest {
	public static void main(String[] args) {
		HashSet<Teacher> hashset = new HashSet<>();
		hashset.add(new Teacher("zhangsan", 2));
		hashset.add(new Teacher("lisi", 1));
		hashset.add(new Teacher("lisi2", 1));
		hashset.add(new Teacher("wangmazi", 3));
		hashset.add(new Teacher("mazi", 3));
		hashset.add(new Teacher("madai", 4));
		hashset.add(new Teacher("赵云", 5));
		hashset.add(new Teacher("典韦", 6));
		hashset.add(new Teacher("周瑜", 7));
		hashset.add(new Teacher("孙坚", 8));
		hashset.add(new Teacher("孙策", 9));
		Iterator it = hashset.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
		
	}
}
