package com.cs.test.week7.forkjoin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

import com.cs.test.week7.file.SearchResult;

public class SearchTextTask extends RecursiveTask<SearchResult> {
	private static final long serialVersionUID = 5198503953670974440L;
	private String searchContent;
	private File file;
	public SearchResult searchResult = new SearchResult();

	public SearchTextTask(String searchContent, File file) {
		this.searchContent = searchContent;
		this.file = file;
	}

	@Override
	protected SearchResult compute() {
		List<SearchTextTask> tasks = new ArrayList<>();
		if (file.isDirectory()) {
			for (File file : file.listFiles()) {
				tasks.add(new SearchTextTask(searchContent, file));
			}
		} else {
			BufferedReader reader = null;
			try {
				FileInputStream inputStream = new FileInputStream(file);
				InputStreamReader isr = new InputStreamReader(inputStream, "UTF-8");
				reader = new BufferedReader(isr);
				String temp;
				int lines = 1;
				int times = 0;
				ConcurrentHashMap<String, Integer> resultMap = searchResult.getMaps();
				while ((temp = reader.readLine()) != null) {
					lines++;
					if (temp.contains(searchContent)) {
						resultMap.put(file.getAbsolutePath(), ++times);
						searchResult.count++;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		try {
			for (ForkJoinTask<SearchResult> result : invokeAll(tasks)) {
				searchResult = searchResult.merge(searchResult, result.join());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		searchResult.setSearchText(searchContent);
		return searchResult;
	}

}
