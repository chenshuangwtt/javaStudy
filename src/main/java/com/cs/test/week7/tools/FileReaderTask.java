package com.cs.test.week7.tools;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel.MapMode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

import org.apache.commons.lang3.StringUtils;

public class FileReaderTask extends RecursiveTask<ConcurrentHashMap<String, Item>> {
	private static final long serialVersionUID = -2295618810696438397L;
	public String charset;
	private int bufferSize;
	private long fileLength;
	private ForkJoinPool forkJoinPool;
	private RandomAccessFile rAccessFile;
	private Set<StartEndPair> startEndPairs;
	List<SliceReaderTask> tasks = new ArrayList<>();
	ConcurrentHashMap<String, Item> resultMap = new ConcurrentHashMap<>();
	
	public  FileReaderTask(File file,  String charset, int bufferSize) {
		this.fileLength = file.length();
		this.charset = charset;
		this.bufferSize = bufferSize;
		forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		try {
			this.rAccessFile = new RandomAccessFile(file, "r");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		startEndPairs = new HashSet<StartEndPair>();
	}

	@Override
	protected ConcurrentHashMap<String, Item> compute() {
		long size = this.fileLength / Runtime.getRuntime().availableProcessors();
		try{
			try {
				calculateStartEnd(0, size);
			} catch (IOException e) {
				e.printStackTrace();
			}
			for (StartEndPair pair : startEndPairs) {
				System.out.println("分配分片：" + pair);
				tasks.add(new SliceReaderTask(pair));
			}
			
			for(Future<ConcurrentHashMap<String, Item>> task : forkJoinPool.invokeAll(tasks)){
				resultMap = groupResult(resultMap,task.get());
			}
			forkJoinPool.shutdown();
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultMap;
	}
	
	private void calculateStartEnd(long start, long size) throws IOException {
		if (start > fileLength - 1) {
			return;
		}
		StartEndPair pair = new StartEndPair();
		pair.start = start;
		long endPosition = start + size - 1;
		if (endPosition >= fileLength - 1) {
			pair.end = fileLength - 1;
			startEndPairs.add(pair);
			return;
		}

		rAccessFile.seek(endPosition);
		byte tmp = (byte) rAccessFile.read();
		while (tmp != '\n' && tmp != '\r') {
			endPosition++;
			if (endPosition >= fileLength - 1) {
				endPosition = fileLength - 1;
				break;
			}
			rAccessFile.seek(endPosition);
			tmp = (byte) rAccessFile.read();
		}
		pair.end = endPosition;
		startEndPairs.add(pair);

		calculateStartEnd(endPosition + 1, size);
	}
	

	public ConcurrentHashMap<String, Item> groupResult(
			ConcurrentHashMap<String, Item> target,
			ConcurrentHashMap<String, Item> plus) {
		if (plus != null) {
			Object[] os = plus.keySet().toArray();
			String key;
			for (int i = 0; i < os.length; i++) {
				key = os[i].toString();
				if (target.containsKey(key)) {
					target.put(key, target.get(key).add(plus.get(key)));
				} else {
					target.put(key, plus.get(key));
				}
			}
		}
		return target;
	}
	
	private static class StartEndPair {
		public long start;
		public long end;
		@Override
		public String toString() {
			return "star=" + start + ";end=" + end;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (int) (end ^ (end >>> 32));
			result = prime * result + (int) (start ^ (start >>> 32));
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			StartEndPair other = (StartEndPair) obj;
			if (end != other.end)
				return false;
			if (start != other.start)
				return false;
			return true;
		}
	}
	
	private void handle(Map<String, Item> resultMap,byte[] bytes) 
			throws UnsupportedEncodingException {
		String line = null;
		if (this.charset == null) {
			line = new String(bytes);
		} else {
			line = new String(bytes, charset);
		}
		if (StringUtils.isNotBlank(line)) {
			String[] tempArr = line.split(",");
			if (resultMap.containsKey(tempArr[0].substring(0, 2))) {
				Item item = resultMap.get(tempArr[0].substring(0, 2));
				item.setSum(item.getSum() + Integer.parseInt(tempArr[1]) * 13 
						+ Integer.parseInt(tempArr[2])); // 计算年薪
				item.setCount(item.getCount() + 1);
			} else {
				Item item = new Item(Integer.parseInt(tempArr[1]) * 13 
						+ Integer.parseInt(tempArr[2]), 1);// 初始化一个item
				resultMap.put(tempArr[0].substring(0, 2), item);
			}
		}
	}
	
	private class SliceReaderTask implements 
							Callable<ConcurrentHashMap<String, Item>> {
		private long start;
		private long sliceSize;
		private byte[] readBuff;

		public SliceReaderTask(StartEndPair pair) {
			this.start = pair.start;
			this.sliceSize = pair.end - pair.start + 1;
			this.readBuff = new byte[bufferSize];
		}
		@Override
		public ConcurrentHashMap<String, Item> call() {
			ConcurrentHashMap<String, Item> resultMap = new ConcurrentHashMap<String, Item>();
			try {
				MappedByteBuffer mapBuffer = rAccessFile.getChannel()
										.map(MapMode.READ_ONLY, start, this.sliceSize);
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				for (int offset = 0; offset < sliceSize; offset += bufferSize) {
					int readLength;
					if (offset + bufferSize <= sliceSize) {
						readLength = bufferSize;
					} else {
						readLength = (int) (sliceSize - offset);
					}
					mapBuffer.get(readBuff, 0, readLength);
					for (int i = 0; i < readLength; i++) {
						byte tmp = readBuff[i];
						if (tmp == '\n' || tmp == '\r') {
							handle(resultMap,bos.toByteArray());
							bos.reset();
						} else {
							bos.write(tmp);
						}
					}
				}
				if (bos.size() > 0) {
					handle(resultMap,bos.toByteArray());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return resultMap;
		}
	}
}
