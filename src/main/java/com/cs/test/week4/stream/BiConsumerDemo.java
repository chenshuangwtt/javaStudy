package com.cs.test.week4.stream;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * java.util.function.BiConsumer is a java 8 functional interface. BiConsumer
 * does not return value. It accepts two parameter as an argument. BiConsumer
 * functional method is accept(Object, Object). This methods performs the
 * operation defined by BiConsumer. In our example we have declared a BiConsumer
 * that will simply print the value of both parameter. To do this we have taken
 * a map which has two parameter key and value. We will print the value of map
 * with the help of BiConsumer
 * 
 *
 */
public class BiConsumerDemo {
	public static void main(String[] args) {
		Map<Integer, String> map = new HashMap<>();
		map.put(1, "A");
		map.put(2, "B");
		map.put(3, "C");
		BiConsumer<Integer, String> biConsumer = (key, value) -> System.out.println("Key:" + key + " Value:" + value);
		map.forEach(biConsumer);
	}
}
