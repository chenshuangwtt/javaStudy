package com.cs.test.week7.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 搜索线程
 */
public class SearchThread implements Runnable {
	public SearchResult searchResult;
	public File file;
	public SearchThread(SearchResult searchResult, File file) {
		this.searchResult = searchResult;
		this.file = file;
	}
@Override
public void run() {
	BufferedReader reader = null;
	try{
		FileInputStream inputStream = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(inputStream,"UTF-8");
		reader =  new BufferedReader(isr);
		String temp;
		int lines = 1;
		int times = 0;
		ConcurrentHashMap<String,Integer> resultMap = searchResult.getMaps();
		while ((temp = reader.readLine())!= null) {
			lines++;
			if (temp.contains(searchResult.getSearchText())) {
				resultMap.put(file.getAbsolutePath(),++times);
				searchResult.count++;
			}
		}
	}catch(Exception e){
		e.printStackTrace();
	}finally {
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
}
