package com.cs.test.week7.file;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 搜索结果
 */
public class SearchResult {
	public String searchText; // 搜索字段
	public volatile int count; // 总共出现次数
	public ConcurrentHashMap<String, Integer> maps = new ConcurrentHashMap<>(); // 出现的详细情况
	@Override
	public String toString() {
		String mapstr = "\n";
		if (maps != null) {
			// 将map.entrySet()转换成list
			List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(maps.entrySet());
			Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
				// 降序排序
				@Override
				public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
					return o2.getValue().compareTo(o1.getValue());
				}
			});
			for (Map.Entry<String, Integer> temp : list) {
				mapstr += temp.getKey() + "-------出现次数" + temp.getValue() + "\n";
			}
		}
		return "SearchResult [searchText=" + searchText + ", count=" + count + ", maps=" + mapstr + "]";
	}

	public SearchResult merge(SearchResult searchResult, 
			SearchResult searchResult2) {
		searchResult.count = searchResult.count + searchResult2.count;
		searchResult.maps = groupResult(searchResult.getMaps(),
								searchResult2.getMaps());
		return searchResult;
	}

	public ConcurrentHashMap<String, Integer> groupResult(
			ConcurrentHashMap<String, Integer> target,
			ConcurrentHashMap<String, Integer> plus) {
		if (plus != null) {
			Object[] os = plus.keySet().toArray();
			String key;
			for (int i = 0; i < os.length; i++) {
				key = os[i].toString();
				if (target.containsKey(key)) {
					target.put(key, target.get(key) + plus.get(key));
				} else {
					target.put(key, plus.get(key));
				}
			}
		}
		return target;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public ConcurrentHashMap<String, Integer> getMaps() {
		return maps;
	}

	public void setMaps(ConcurrentHashMap<String, Integer> maps) {
		this.maps = maps;
	}
}
