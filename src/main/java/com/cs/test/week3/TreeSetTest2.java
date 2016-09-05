package com.cs.test.week3;

import java.util.Iterator;
import java.util.*;

//使用对象自带比较器
public class TreeSetTest2 {
	public static void main(String[] args) {
		TreeSet ts = new TreeSet(new Teacher2.TeacherCompare());
		ts.add(new Teacher2("zhangsan", 2));
		ts.add(new Teacher2("lisi", 1));
		ts.add(new Teacher2("wangmazi", 3));
		ts.add(new Teacher2("mazi", 3));
		Iterator it = ts.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}
}