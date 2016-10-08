package com.cs.test.week7.juc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class ExecutorServiceDemo {
	public static void main(String[] args) throws IOException {
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		List<Future<Long>> futures = Files.list(new File("D:/").toPath()).filter(s -> !s.toFile().isDirectory())
				.map(s -> new Callable<Long>() {
					@Override
					public Long call() throws Exception {
						System.out.println(s);
						return Files.size(s);
					}
				}).map(c -> executorService.submit((Callable<Long>) c)).collect(Collectors.toList());
		// 获取结果
		Supplier<LongStream> streamSupplier = () -> futures.stream().map(f -> {
			try {
				return f.get();
			} catch (Exception e) {
				e.printStackTrace();
				return -1;
			}
		}).mapToLong(val -> (long) val);
		streamSupplier.get().forEach(System.out::println);
		System.out.println("total:" + streamSupplier.get().sum());

	}
}
