package com.cs.test.week7.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DirSize2 {
	private static final Logger LOGGER = LoggerFactory.getLogger(DirSize2.class);

	private static class SizeOfFileTask extends RecursiveTask<Long> {

		private static final long serialVersionUID = -3306666662204274644L;
		private final File file;

		public SizeOfFileTask(final File file) {
			this.file = Objects.requireNonNull(file);
		}

		@Override
		protected Long compute() {
			DirSize2.LOGGER.debug("Computing size of: {}", file);
			if (file.isFile()) {
				return file.length();
			}

			final List<SizeOfFileTask> tasks = new ArrayList<>();
			final File[] children = file.listFiles();
			if (children != null) {
				for (final File child : children) {
					final SizeOfFileTask task = new SizeOfFileTask(child);
					task.fork();
					tasks.add(task);
				}
			}
			long size = 0;
			for (final SizeOfFileTask task : tasks) {
				size += task.join();
			}
			return size;
		}
	}
	
	public static long sizeOf(final File file){
		final ForkJoinPool forkJoinPool = new ForkJoinPool();
		try {
			return forkJoinPool.invoke(new SizeOfFileTask(file));
		} finally{
			forkJoinPool.shutdown();
		}
	}
	private DirSize2() {}

}
