package com.cs.test.week7.filesearch;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class NameFilter extends AbstractFileFilter {
    public static Map<String, Integer> allFilesMap = new HashMap<>();
    public static Map<String, Integer> fileNameMap = new HashMap<>();

    public NameFilter(int priority, String param) {
        super(priority, param);
    }

    @Override
    public void filtrationByParam(String param) {
        System.out.println("--1--Find By File Name----");

        fileNameMap =
                allFilesMap.entrySet().stream()
                        .filter(s -> s.getKey().toLowerCase().contains(param.toLowerCase()))
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                e -> Integer.parseInt(String.valueOf(e.getValue()))
                        ));

        if (fileNameMap.size() == 0) {
            System.out.println("Not found files by filter: name ");
            System.exit(0);
        }
    }
}