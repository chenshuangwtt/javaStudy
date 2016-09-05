package com.cs.test.week4.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CreateStreamDemo {
	public static void main(String[] args) {
		List<String> memberNames = new ArrayList<>();
		memberNames.add("Amitabh");
		memberNames.add("Shekhar");
		memberNames.add("Aman");
		memberNames.add("Rahul");
		memberNames.add("Shahrukh");
		memberNames.add("Salman");
		memberNames.add("Yana");
		memberNames.add("Lokesh");
		System.err.println("filter()测试:");
		memberNames.stream().filter((s) -> s.startsWith("A"))
				.forEach(System.out::println);
		System.err.println("map()测试:");
		memberNames.stream().filter((s) -> s.startsWith("A"))
				.map(String::toUpperCase).forEach(System.out::println);
		System.err.println("sorted()测试:");
		memberNames.stream().sorted().map(String::toUpperCase)
				.forEach(System.out::println);
		System.err.println("forEach()测试:");
		memberNames.forEach(System.out::println);
		System.err.println("collect()测试:");
		List<String> memNamesInUppercase = memberNames.stream().sorted().map(String::toUpperCase)
				.collect(Collectors.toList());
		System.out.println(memNamesInUppercase);
		System.err.println("match()测试:");
		boolean matchedResult = memberNames.stream().anyMatch((s) -> s.startsWith("A"));
		System.out.println(matchedResult);
		matchedResult = memberNames.stream().allMatch((s) -> s.startsWith("A"));
		System.out.println(matchedResult);
		matchedResult = memberNames.stream().noneMatch((s) -> s.startsWith("A"));
		System.out.println(matchedResult);
		System.err.println("count()测试:");
		long totalMatched = memberNames.stream().filter((s) -> s.startsWith("A")).count();
		System.out.println(totalMatched);
		System.err.println("reduce()测试:");
		Optional<String> reduced = memberNames.stream().reduce((s1, s2) -> s1 + "#" + s2);
		reduced.ifPresent(System.out::println);
		boolean matched = memberNames.stream().anyMatch((s) -> s.startsWith("A"));
		System.out.println(matched);
		String firstMatchedName = memberNames.stream().filter((s) -> s.startsWith("L")).findFirst().get();
		System.out.println(firstMatchedName);
	}
}
