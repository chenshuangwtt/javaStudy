package com.cs.test.week4;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamDemo {
	public static void main(String[] args) {
		// 静态方法
		Stream<Integer> integerStream = Stream.of(1, 2, 3, 5);
		Stream<String> stringStream = Stream.of("taobao");

		// generator方法
		Stream.generate(new Supplier<Double>() {
			@Override
			public Double get() {
				return Math.random();
			}
		});
		Stream.generate(() -> Math.random());
		Stream.generate(Math::random);
		// iterate方法
		Stream.iterate(1, item -> item + 1).limit(10).forEach(System.out::println);

		IntStream.of(new int[] { 1, 2, 3 }).forEach(System.out::println);
		IntStream.range(1, 3).forEach(System.out::println);
		IntStream.rangeClosed(1, 3).forEach(System.out::println);

		arraysStream();
		collectionStream();
		generate();
		iterateStream();
		populaStream();

	}

	public static void arraysStream() {
		String[] arr = { "program", "creek", "program", "creek", "java", "web", "program" };
		Stream<String> stream = Stream.of(arr);
		System.out.println(Arrays.toString(arr));
	}

	// From Collections
	public static void collectionStream() {
		List<String> list = new ArrayList<>();
		list.add("Java");
		list.add("python");
		list.add("c++");
		list.add("c");
		list.add("lisp");
		Stream<String> stream = list.stream().filter(p -> p.length() > 3);
		String[] arr = stream.toArray(String[]::new);
		System.out.println(Arrays.toString(arr));
	}

	// Use Stream.generate()
	public static void generate() {
		Stream<String> stream = Stream.generate(() -> "test").limit(10);
		String[] strArr = stream.toArray(String[]::new);
		System.out.println(Arrays.toString(strArr));
	}

	// Use Stream.iterate()
	public static void iterateStream() {
		Stream<BigInteger> bigIntStream = Stream.iterate(BigInteger.ZERO, n -> n.add(BigInteger.TEN)).limit(10);
		BigInteger[] bigIntArr = bigIntStream.toArray(BigInteger[]::new);
		System.out.println(Arrays.toString(bigIntArr));
	}

	// From Popular APIs
	public static void populaStream() {
		String sentence = "Program creek is a Java site.";
		Stream<String> wordStream = Pattern.compile("\\W").splitAsStream(sentence);
		String[] wordArr = wordStream.toArray(String[]::new);
		System.out.println(Arrays.toString(wordArr));
	}
}
