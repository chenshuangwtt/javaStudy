package com.cs.test.week12.reactor;

import java.io.File;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;


public class SearchMain {
	private ForkJoinPool pool;
	private String homeDir;
	private String key;

	public SearchMain(String key, String homeDir) {
		this.pool = new ForkJoinPool();
		this.homeDir = homeDir;
		this.key = key;
	}

	public String search() throws InterruptedException, ExecutionException{
		SearchTextTask task = new SearchTextTask(key, new File(homeDir));
		pool.invoke(task);
		return (task.get().toString());
	}
	
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		System.err.println(System.getProperty("user.dir"));
		SearchMain searchMain = new SearchMain("System", System.getProperty("user.dir"));
		System.out.println(searchMain.search());
	}
}
