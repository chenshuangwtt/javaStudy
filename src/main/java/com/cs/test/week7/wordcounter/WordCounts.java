package com.cs.test.week7.wordcounter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

public class WordCounts {
	private final Map<String, AtomicInteger> m;

	public WordCounts(int parLevel) {
		this.m = (parLevel == 1) ? new HashMap<String, AtomicInteger>()
				: new ConcurrentHashMap<String, AtomicInteger>(4096, 0.75f, parLevel);
	}

	public int getSize() {
		return m.size();
	}

	public void add(String word, int count) {
		AtomicInteger cc = m.get(word);
		if (cc != null) {
			cc.addAndGet(count);
		}else{
			if (m instanceof ConcurrentMap) {
				 cc = ((ConcurrentMap<String, AtomicInteger>) m).putIfAbsent(word, new AtomicInteger(count));
				 if (cc != null) {
	                    cc.addAndGet(count);
	             }
			}else{
				m.put(word, new AtomicInteger(count));
			}
		}
	}
	
	public void add(WordCounts wc){
		for(Map.Entry<String , AtomicInteger> entry : wc.m.entrySet()){
			add(entry.getKey(), entry.getValue().get());
		}
	}
	
	

}
