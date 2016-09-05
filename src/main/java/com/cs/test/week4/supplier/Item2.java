package com.cs.test.week4.supplier;

public class Item2 {
	private String name;

	public Item2() {
	}

	public Item2(String name) {
		this.name = name;
	}

	public static String getStaticVal() {
		return "Static Val";
	}

	public String getMsg() {
		return "Hello World!";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
