package com.cs.test.week4;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Consumer;

public class ParallelSortWithComparator {
	public static void main(String[] args) {
		User[] users = User.getUsers();
		Comparator<User> ageComparator = Comparator.comparing(User::getAge);
		System.out.println("--Sort complete array--");
		Arrays.parallelSort(users, ageComparator);
		Consumer<User> printUser = u-> System.out.println(u.getName()+"-"+u.getAge());
		Arrays.stream(users).forEach(printUser);
		System.out.println("--Sort array from index 1 to 4--");
		users = User.getUsers();
		Arrays.parallelSort(users, 1, 4, ageComparator);
		Arrays.stream(users).forEach(printUser);
	}
}
