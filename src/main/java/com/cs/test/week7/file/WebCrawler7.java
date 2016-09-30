package com.cs.test.week7.file;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.concurrent.ForkJoinPool;

public class WebCrawler7 implements LinkHandler {
	private final Collection<String> visitedLinks = Collections.synchronizedSet(new HashSet<String>());
	// private final Collection<String> visitedLinks =
	// Collections.synchronizedList(new ArrayList<>());
	private String url;
	private ForkJoinPool mainPool;

	public WebCrawler7(String startingURL, int maxThreads) {
		this.url = startingURL;
		mainPool = new ForkJoinPool(maxThreads);
	}

	private void startCrawling() {
		mainPool.invoke(new LinkFinderAction(this.url, this));
	}

	@Override
	public void queueLink(String link) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public int size() {
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

	public static void main(String[] args) throws Exception {
		new WebCrawler7("http://www.javaworld.com", 64).startCrawling();
	}
}
