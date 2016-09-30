package com.cs.test.week7.file;

import java.io.File;
import java.util.Objects;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DirSize4 {
	private static class SizeOfFileAction extends RecursiveAction {

		private static final long serialVersionUID = -196522408291343951L;

		private final File file;
		private final AtomicLong sizeAccumulator;

		public SizeOfFileAction(final File file, final AtomicLong sizeAccumulator) {
			this.file = Objects.requireNonNull(file);
			this.sizeAccumulator = Objects.requireNonNull(sizeAccumulator);
		}

		@Override
		protected void compute() {
			DirSize4.LOGGER.debug("Computing size of: {}", file);
			if (file.isFile()) {
				sizeAccumulator.addAndGet(file.length());
			} else {
				final File[] children = file.listFiles();
				if (children != null) {
					for (final File child : children) {
						ForkJoinTask.invokeAll(new SizeOfFileAction(child, sizeAccumulator));
					}
				}
			}
		}
	}

	public static long sizeOf(final File file) {
		final ForkJoinPool pool = new ForkJoinPool();
		try {
			final AtomicLong sizeAccumulator = new AtomicLong();
			pool.invoke(new SizeOfFileAction(file, sizeAccumulator));
			return sizeAccumulator.get();
		} finally {
			pool.shutdown();
		}
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(DirSize4.class);

	private DirSize4() {
	}
}
