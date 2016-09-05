package com.cs.test.week4;

public class User implements Comparable<User> {
	private String name;
	private int age;

	public User(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	@Override
	public int compareTo(User user) {
		return name.compareTo(user.name);
	}

	public static User[] getUsers() {
		User[] users = new User[6];
		users[0] = new User("Ram", 25);
		users[1] = new User("Shyam", 22);
		users[2] = new User("Mohan", 21);
		users[3] = new User("Suresh", 30);
		users[4] = new User("Ramesh", 20);
		users[5] = new User("Dinesh", 27);
		return users;
	}
}