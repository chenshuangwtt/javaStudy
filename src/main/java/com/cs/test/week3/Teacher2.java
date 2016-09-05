package com.cs.test.week3;

import java.util.Comparator;

public class Teacher2 {
	int num;
	String name;

	Teacher2(String name, int num) {
		this.num = num;
		this.name = name;
	}

	public String toString() {
		return "学号：" + num + "    姓名：" + name;
	}
	
	static class TeacherCompare implements Comparator {// 老师自带的一个比较器
		// o1中存放的事目标节点
		// o2中存放时的红黑二叉树中的节点，从根节点开始比较
		public int compare(Object o1, Object o2) {
			Teacher2 s1 = (Teacher2) o1;// 转型
			Teacher2 s2 = (Teacher2) o2;// 转型
			int result = s1.num > s2.num ? 1 : (s1.num == s2.num ? 0 : -1);
			if (result == 0) {
				result = s1.name.compareTo(s2.name);
			}
			return result;
		}
	}
}