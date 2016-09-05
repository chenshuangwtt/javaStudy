package com.cs.test.week4;

public class Person2 {
	private int pid;
	private String name;

	public Person2() {}

	public Person2(int pid, String name){
		this.pid = pid;
		this.name = name;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
