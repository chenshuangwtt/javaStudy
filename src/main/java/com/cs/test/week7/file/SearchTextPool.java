package com.cs.test.week7.file;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SearchTextPool {
	public static SearchResult searchResult = new SearchResult();
	public static ExecutorService executorService ;
	
	public static void main(String[] args) {
		int coreNum = Runtime.getRuntime().availableProcessors();
		executorService = Executors.newFixedThreadPool(coreNum);
		File homeDir = null; //new File("E:\\eclipse_workspace\\javaStudy\\src\\main\\java\\com\\cs\\test\\week7\\queue");
		if (homeDir == null) {
			homeDir = new File(System.getProperty("user.dir"));
		}
		searchResult.searchText ="System";
		searchDir(homeDir);
		executorService.shutdown();
		System.out.println(searchResult.toString());
	}
	/**
	 * 文件目录搜索
	 * @param f
	 */
	public static void searchDir(File f) {
		if (!f.isDirectory()) {
			searchFile(f);
			return;
		}
		for (File file : f.listFiles()) {
			if (file.isDirectory()) {
				searchDir(file);
			} else {
				searchFile(file);
			}
		}
	}

	private static void searchFile(File file) {
		SearchThread searchThread = new SearchThread(searchResult, file);
		executorService.submit(searchThread);
	}
}
