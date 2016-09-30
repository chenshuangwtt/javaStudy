package com.cs.test.week7.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DirSize3 {
	private static final Logger LOGGER = LoggerFactory.getLogger(DirSize3.class);

	private static class SizeOfFileCallable implements Callable<Long> {
		private final File file;
		private final ExecutorService executorService;

		public SizeOfFileCallable(final File file, final ExecutorService service) {
			this.file = Objects.requireNonNull(file);
			this.executorService = Objects.requireNonNull(service);
		}

		@Override
		public Long call() throws Exception {
			DirSize3.LOGGER.debug("Computing size of: {}", file);
			long size = 0;

			if (file.isFile()) {
				size = file.length();
			} else {
				final List<Future<Long>> futures = new ArrayList<>();
				for (final File child : file.listFiles()) {
					futures.add(executorService.submit(new SizeOfFileCallable(child, executorService)));
				}

				for (final Future<Long> future : futures) {
					size += future.get();
				}
			}
			return size;
		}
	}

	public static <T> long sizeof(final File file) {
		final int threads = Runtime.getRuntime().availableProcessors();
		DirSize3.LOGGER.debug("Creating executor with {} threads", threads);
		final ExecutorService executor = Executors.newFixedThreadPool(threads);
		try {
			return executor.submit(new SizeOfFileCallable(file, executor)).get();
		} catch (final Exception e) {
			throw new RuntimeException("Failed to calculate the dir size", e);
		} finally {
			executor.shutdown();
		}
	}

	 private DirSize3() {}
}
