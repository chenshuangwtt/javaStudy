package com.cs.test.week3;

public class Teacher {
	int num;
	String name;
	Teacher(String name, int num) {
		this.num = num;
		this.name = name;
	}

	public String toString() {
		return "学号：" + num + "\t\t姓名：" + name;
	}
}