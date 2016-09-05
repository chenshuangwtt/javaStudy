package com.cs.test.week4.statistics;

import java.util.DoubleSummaryStatistics;

public class DoubleSummaryStatisticsDemoTwo {
	public static void main(String[] args) {
		DoubleSummaryStatistics dblSumStatOne = new DoubleSummaryStatistics();
		dblSumStatOne.accept(123.34);
		dblSumStatOne.accept(100.25);
		dblSumStatOne.accept(20.55);
		// Find the average
		System.out.println("Average:" + dblSumStatOne.getAverage());
		// Find count
		System.out.println("Count:" + dblSumStatOne.getCount());
		DoubleSummaryStatistics dblSumStatTwo = new DoubleSummaryStatistics();
		dblSumStatTwo.accept(323.84);
		dblSumStatTwo.accept(67.14);
		// Combine another DoubleSummaryStatistics
		dblSumStatOne.combine(dblSumStatTwo);
		System.out.println("Average and Count after combining second DoubleSummaryStatistics");
		// Average after combining second DoubleSummaryStatistics
		System.out.println("Average:" + dblSumStatOne.getAverage());
		// Count after combining second DoubleSummaryStatistics
		System.out.println("Count:" + dblSumStatOne.getCount());
	}
}
