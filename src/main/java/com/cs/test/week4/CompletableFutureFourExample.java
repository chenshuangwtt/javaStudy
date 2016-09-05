package com.cs.test.week4;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CompletableFutureFourExample {
	public static void main(String[] args) {
		List<String> list = Arrays.asList("A", "B", "C", "D");
		list.stream().map(s -> CompletableFuture.supplyAsync(() -> s + s)).map(f -> f.getNow("Not Done"))
				.forEach(s -> System.out.println(s));
	}
	
	
	
}
