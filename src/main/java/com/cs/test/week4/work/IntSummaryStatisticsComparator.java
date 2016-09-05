package com.cs.test.week4.work;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.Map;
import java.util.Map.Entry;

public class IntSummaryStatisticsComparator 
		implements Comparator<Map.Entry<String,IntSummaryStatistics>> {

	public int compare(Entry<String, IntSummaryStatistics> o1, Entry<String, IntSummaryStatistics> o2) {
		if (o1.getValue().getSum() < o2.getValue().getSum()) {
			return 1;
		}else if (o1.getValue().getSum() > o2.getValue().getSum()) {
			return -1;
		}else{
			return 0;
		}
	}
}