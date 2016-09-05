package com.cs.test.week4.work;

import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class SalaryCollector
		implements Collector<String, HashMap<String, IntSummaryStatistics>, HashMap<String, IntSummaryStatistics>> {

	@Override
	public Supplier<HashMap<String, IntSummaryStatistics>> supplier() {
		return HashMap<String, IntSummaryStatistics>::new;
	}

	@Override
	public BiConsumer<HashMap<String, IntSummaryStatistics>, String> accumulator() {
		return (map, line) -> {
			String[] tempArr = line.split(",");
			String key = tempArr[0].substring(0, 2);
			IntSummaryStatistics item = map.get(key);
			int value = Integer.parseInt(tempArr[1]) * 13 + Integer.parseInt(tempArr[2]);
			if (item == null) {
				item = new IntSummaryStatistics();
			}
			item.accept(value);
			map.put(key, item);
		};
	}
	
	@Override
	public BinaryOperator<HashMap<String, IntSummaryStatistics>> combiner() {
		return (map1, map2) -> {
			map1.putAll(map2);
			return map1;
		};
	}

	@Override
	public Function<HashMap<String, IntSummaryStatistics>, HashMap<String, IntSummaryStatistics>> finisher() {
		return Function.identity();
	}

	@Override
	public Set<java.util.stream.Collector.Characteristics> characteristics() {
		return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
	}

}
