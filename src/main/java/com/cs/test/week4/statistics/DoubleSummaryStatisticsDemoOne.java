package com.cs.test.week4.statistics;

import java.util.DoubleSummaryStatistics;

/**
 * accept() accept() method adds the element and is available in both
 * DoubleSummaryStatistics and IntSummaryStatistics class. 
 * 
 * getAverage()
 * getAverage() method returns average of all accepted value as a double. This
 * method is available in both the class DoubleSummaryStatistics and
 * IntSummaryStatistics. 
 * 
 * getCount() getCount() method calculates the count of
 * all element and is available in both the class the class
 * DoubleSummaryStatistics and IntSummaryStatistics. 
 * 
 * getMax() Using getMax()method we can get the maximum value which has been added. 
 * This method is
 * present in both the class DoubleSummaryStatistics and IntSummaryStatistics.
 * 
 * getMin() Using getMin() method we can get the minimum value which has been
 * added and is available in both the class DoubleSummaryStatistics and
 * IntSummaryStatistics. 
 * 
 * getSum() getSum() method provides the sum of all
 * elements added. We can get this method in both the class
 * DoubleSummaryStatistics and IntSummaryStatistics. 
 * 
 * combine() We can combine
 * two instances of DoubleSummaryStatistics and then can get all statistics. And
 * in the same way IntSummaryStatistics provides combine() method.
 * 
 * @author Administrator
 *
 */
public class DoubleSummaryStatisticsDemoOne {
	public static void main(String[] args) {
		DoubleSummaryStatistics dblSumStat = new DoubleSummaryStatistics();
		dblSumStat.accept(123.34);
		dblSumStat.accept(100.25);
		dblSumStat.accept(20.55);
		dblSumStat.accept(323.84);
		dblSumStat.accept(67.14);
		// Find the average
		System.out.println("Average:" + dblSumStat.getAverage());
		// Find maximum
		System.out.println("Maximum:" + dblSumStat.getMax());
		// Find minimum
		System.out.println("Minimum:" + dblSumStat.getMin());
		// Find Sum
		System.out.println("Sum:" + dblSumStat.getSum());
		// Find count
		System.out.println("Count:" + dblSumStat.getCount());
	}
}
