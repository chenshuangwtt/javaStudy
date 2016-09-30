package com.cs.test.week7.file;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WebCrawler6 implements LinkHandler {

	private final Collection<String> visitedLinks = Collections.synchronizedSet(new HashSet<String>());
	private String url;
	private ExecutorService execService;

	public WebCrawler6(String startingURL, int maxThreads) {
		this.url = startingURL;
		execService = Executors.newFixedThreadPool(maxThreads);
	}

	@Override
	public void queueLink(String link) throws Exception {
		startNewThread(link);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return visitedLinks.size();
	}

	@Override
	public boolean visited(String link) {
		return visitedLinks.contains(link);
	}

	@Override
	public void addVisited(String link) {
		visitedLinks.add(link);
	}

	private void startNewThread(String link) throws Exception {
		execService.execute(new LinkFinder(link, this));
	}

	public static void main(String[] args) throws Exception {
//		new WebCrawler("http://www.javaworld.com", 64).startCrawling();
	}
}
