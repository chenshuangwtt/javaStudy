package com.cs.test.week7.filesearch;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Provides a filtration by size file
 */
public class SizeFilter extends AbstractFileFilter {
    public static Map<String, Integer> fileSizeMap = new HashMap<>();
    public static Map<String, Integer> fileNameMap = new HashMap<>();

    public SizeFilter(int priority, String param) {
        super(priority, param);
    }

    @Override
    public void filtrationByParam(String param) {
        System.out.println("--2--Find By Size Name----");

        try {
            fileSizeMap =
                    fileNameMap.entrySet().stream()
                            .filter(s -> s.getValue() < Integer.parseInt(param))
                            .collect(Collectors.toMap(
                                    Map.Entry::getKey,
                                    e -> Integer.parseInt(String.valueOf(e.getValue()))
                            ));
        } catch (NumberFormatException ex) {
            System.out.println("You input a very big number!");
        }

        if (fileSizeMap.size() == 0) {
            System.out.println("Not found files by filter: size ");
            System.exit(0);
        }
    }
}