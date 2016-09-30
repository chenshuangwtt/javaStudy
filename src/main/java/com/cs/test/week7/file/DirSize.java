package com.cs.test.week7.file;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Using a Single Thread (no-concurrency)
 */
public class DirSize {
	private static final Logger LOGGER = LoggerFactory.getLogger(DirSize.class);

	public static long sizeOf(final File file) {
		DirSize.LOGGER.debug("Computing size of: {}", file);

		long size = 0;

		// Ignore files which are not files and dirs
		if (file.isFile()) {
			size = file.length();
		} else {
			final File[] children = file.listFiles();
			if (children != null) {
				for (final File child : children) {
					size += DirSize.sizeOf(child);
				}
			}
		}
		return size;
	}
}
