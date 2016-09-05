package com.cs.test.week4;

public class Book2 {
	public int id;
	public String name;

	public Book2(int id, String name){
        this.id = id;
        this.name = name;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void print() {
		System.out.println("id:" + id + ", Name:" + name);
	}
}
