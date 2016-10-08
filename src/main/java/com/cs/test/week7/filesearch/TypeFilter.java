package com.cs.test.week7.filesearch;

import java.util.HashMap;
import java.util.Map;

/**
 * Provides a filtration by type file
 */
public class TypeFilter extends AbstractFileFilter {
	public static Map<String, Integer> typeFilesMap = new HashMap<>();
	public static Map<String, Integer> fileSizeMap = new HashMap<>();

	public TypeFilter(int priority, String param) {
		super(priority, param);
	}

	@Override
	public void filtrationByParam(String param) {
		System.out.println("--3--Find By Type Name----");

		fileSizeMap.entrySet()
				.stream().filter(entry -> (new StringBuilder(entry.getKey())
						.substring(entry.getKey().lastIndexOf('.') + 1).trim()).equals(param))
				.forEach(entry -> typeFilesMap.put(entry.getKey(), entry.getValue()));

		if (typeFilesMap.size() == 0) {
			System.out.println("Not found files by filter: type ");
			System.exit(0);
		}
		typeFilesMap.entrySet().stream().forEach(System.out::println);
	}

}
