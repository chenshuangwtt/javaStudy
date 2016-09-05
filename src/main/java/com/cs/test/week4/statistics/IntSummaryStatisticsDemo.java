package com.cs.test.week4.statistics;

import java.util.IntSummaryStatistics;

public class IntSummaryStatisticsDemo {
	public static void main(String[] args) {
		IntSummaryStatistics intSumStatOne = new IntSummaryStatistics();
		intSumStatOne.accept(123);
		intSumStatOne.accept(100);
		intSumStatOne.accept(20);
		// Find the average
		System.out.println("Average:" + intSumStatOne.getAverage());
		// Find count
		System.out.println("Count:" + intSumStatOne.getCount());
		IntSummaryStatistics intSumStatTwo = new IntSummaryStatistics();
		intSumStatTwo.accept(323);
		intSumStatTwo.accept(67);
		// Combine another IntSummaryStatistics
		intSumStatTwo.combine(intSumStatTwo);
		System.out.println("Average and Count after combining second IntSummaryStatistics");
		// Average after combining second IntSummaryStatistics
		System.out.println("Average:" + intSumStatTwo.getAverage());
		// Count after combining second IntSummaryStatistics
		System.out.println("Count:" + intSumStatTwo.getCount());
	}
}
