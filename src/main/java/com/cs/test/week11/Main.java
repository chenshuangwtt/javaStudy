package com.cs.test.week11;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

public class Main {
	public static void deleteCopied(Path path) {
		try {
			Files.deleteIfExists(path);
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}

	public static void main(String[] args) {
		final Path copy_from = Paths.get("F:/800M.file");
		final Path copy_to = Paths.get("F:/tmp/800M.file");
		long startTime, endTime;
		int bufferSizeKB = 4;
		int bufferSize = bufferSizeKB * 1024;
		deleteCopied(copy_to);

		// FileChannel and direct buffer
		System.out.println("Using FileChannel and non-direct buffer ...");
		try (FileChannel fileChannel_from = (FileChannel.open(copy_from, EnumSet.of(StandardOpenOption.READ)));
				FileChannel fileChannel_to = (FileChannel.open(copy_to,
						EnumSet.of(StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE)))) {
			startTime = System.currentTimeMillis();
			// Allocate a non-direct Bytebuffer
			ByteBuffer byteBuffer = ByteBuffer.allocate(bufferSize);
			// Read data from file into ByteBuffer
			int bytesCount;
			while ((bytesCount = fileChannel_from.read(byteBuffer)) > 0) {
				// flip the buffer which set the limit to current position, and
				// position to 0
				byteBuffer.flip();
				// write data from ByteBuffer to file
				fileChannel_to.write(byteBuffer);
				// for the next read
				byteBuffer.clear();
			}
			endTime = System.currentTimeMillis();
			System.out.println("Cost Time is " + (endTime-startTime) + " seconds");
		} catch (IOException ex) {
			System.err.println(ex);
		}

		deleteCopied(copy_to);
		// FileChannel and direct buffer
		System.out.println("Using FileChannel and direct buffer ...");
		try (FileChannel fileChannel_from = (FileChannel.open(copy_from, EnumSet.of(StandardOpenOption.READ)));
				FileChannel fileChannel_to = (FileChannel.open(copy_to,
						EnumSet.of(StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE)))) {

			startTime = System.currentTimeMillis();

			// Allocate a direct ByteBuffer
			ByteBuffer bytebuffer = ByteBuffer.allocateDirect(bufferSize);

			// Read data from file into ByteBuffer
			int bytesCount;
			while ((bytesCount = fileChannel_from.read(bytebuffer)) > 0) {
				// flip the buffer which set the limit to current position, and
				// position to 0
				bytebuffer.flip();
				// write data from ByteBuffer to file
				fileChannel_to.write(bytebuffer);
				// for the next read
				bytebuffer.clear();
			}
			System.out.println("cost Time is " + (System.currentTimeMillis() - startTime) + " seconds");
		} catch (IOException ex) {
			System.err.println(ex);
		}

		deleteCopied(copy_to);
		// FileChannel.transferTo()
		System.out.println("Using FileChannel.transferTo method ...");
		try (FileChannel fileChannel_from = (FileChannel.open(copy_from, EnumSet.of(StandardOpenOption.READ)));
				FileChannel fileChannel_to = (FileChannel.open(copy_to,
						EnumSet.of(StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE)))) {
			startTime = System.currentTimeMillis();
			fileChannel_from.transferTo(0L, fileChannel_from.size(), fileChannel_to);
			endTime = System.currentTimeMillis();
			System.out.println("cost Time is " + (endTime - startTime) + " seconds");
		} catch (IOException e) {
			System.err.println(e);
		}
		
		deleteCopied(copy_to);
		// FileChannel.transferFrom()
		System.out.println("Using FileChannel.transferFrom method ...");
		try (FileChannel fileChannel_from = (FileChannel.open(copy_from, EnumSet.of(StandardOpenOption.READ)));
				FileChannel fileChannel_to = (FileChannel.open(copy_to,
						EnumSet.of(StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE)))) {
			startTime = System.currentTimeMillis();
			fileChannel_to.transferFrom(fileChannel_from, 0L, (int) fileChannel_from.size());
			endTime = System.currentTimeMillis();
			System.out.println("cost Time is " + (endTime - startTime) + " seconds");
		} catch (IOException ex) {
			System.err.println(ex);
		}

		
		deleteCopied(copy_to);
		// FileChannel.map
		System.out.println("Using FileChannel.map method ...");
		try (FileChannel fileChannel_from = (FileChannel.open(copy_from, EnumSet.of(StandardOpenOption.READ)));
				FileChannel fileChannel_to = (FileChannel.open(copy_to,
						EnumSet.of(StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE)))) {
			startTime = System.currentTimeMillis();
			MappedByteBuffer buffer = fileChannel_from.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel_from.size());
			fileChannel_to.write(buffer);
			buffer.clear();
			endTime = System.currentTimeMillis();
			System.out.println("cost Time is " + (endTime - startTime) + " seconds");
		} catch (IOException ex) {
			System.err.println(ex);
		}

		deleteCopied(copy_to);
		// Buffered Stream I/O
		System.out.println("Using buffered streams and byte array ...");
		File inFileStr = copy_from.toFile();
		File outFileStr = copy_to.toFile();
		try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(inFileStr));
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outFileStr))) {
			startTime = System.currentTimeMillis();
			byte[] byteArray = new byte[bufferSize];
			int bytesCount;
			while ((bytesCount = in.read(byteArray)) != -1) {
				out.write(byteArray, 0, bytesCount);
			}
			endTime = System.currentTimeMillis();
			System.out.println("cost Time is " + (endTime - startTime) + " seconds");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		deleteCopied(copy_to);
		System.out.println("Using un-buffered streams and byte array ...");
		try (FileInputStream in = new FileInputStream(inFileStr);
				FileOutputStream out = new FileOutputStream(outFileStr)) {
			startTime = System.currentTimeMillis();
			byte[] byteArray = new byte[bufferSize];
			int bytesCount;
			while ((bytesCount = in.read(byteArray)) != -1) {
				out.write(byteArray, 0, bytesCount);
			}
			endTime = System.currentTimeMillis();
			System.out.println("cost Time is " + (endTime - startTime) + " seconds");
		} catch (IOException ex) {
			System.err.println(ex);
		}

		deleteCopied(copy_to);
		System.out.println("Using Files.copy (Path to Path) method ...");
		try {
			startTime = System.currentTimeMillis();
			Files.copy(copy_from, copy_to, LinkOption.NOFOLLOW_LINKS);
			endTime = System.currentTimeMillis();
			System.out.println("Elapsed Time is " + (endTime - startTime) + " seconds");
		} catch (IOException e) {
			System.err.println(e);
		}

		deleteCopied(copy_to);
		System.out.println("Using Files.copy (InputStream to Path) ...");
		try (InputStream is = new FileInputStream(copy_from.toFile())) {
			startTime = System.currentTimeMillis();
			Files.copy(is, copy_to);
			endTime = System.currentTimeMillis();
			System.out.println("Cost Time is " + (endTime - startTime) + " seconds");
		} catch (IOException e) {
			System.err.println(e);
		}

		deleteCopied(copy_to);
		System.out.println("Using Files.copy (Path to OutputStream) ...");
		try (OutputStream os = new FileOutputStream(copy_to.toFile())) {
			startTime = System.currentTimeMillis();
			Files.copy(copy_from, os);
			endTime = System.currentTimeMillis();
			System.out.println("cost Time is " + (endTime - startTime) + " seconds");
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
