package com.cs.test.week7;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ConcurrentDemo1 {

	private static final ConcurrentMap<String, ConcurrentDemo1> map = new ConcurrentHashMap<String, ConcurrentDemo1>();
	private static ConcurrentDemo1 instance;

	public static ConcurrentDemo1 getInstance() {
		if (instance == null) {
			map.putIfAbsent("INSTANCE", new ConcurrentDemo1());
			instance = map.get("INSTANCE");
		}
		return instance;
	}

	private ConcurrentDemo1() {
	}

}