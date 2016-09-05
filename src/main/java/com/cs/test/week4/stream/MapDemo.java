package com.cs.test.week4.stream;

import java.util.List;
import java.util.stream.Stream;

/**
 * Stream.map transforms one stream into another stream on the basis of given
 * function. In the example we have an employee stream and then we converted
 * into player stream using map
 * 
 */
public class MapDemo {
	public static void main(String[] args) {
		List<Employee> list = Employee.getEmpList();
		Stream<Player> players = list.stream().map(e -> new Player(e.id, e.name));
		players.forEach(p -> System.out.println(p.id + ", " + p.name));
	}
	static class Player {
		int id;
		String name;

		Player(int id, String name) {
			this.id = id;
			this.name = name;
		}
	}
}
