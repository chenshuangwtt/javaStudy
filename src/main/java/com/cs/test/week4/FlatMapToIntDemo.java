package com.cs.test.week4;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class FlatMapToIntDemo {
	public static void main(String[] args) {
		int[][] data = {{1,2},{3,4},{5,6}}; //{1,2,3,4,5,6}
		IntStream is1 = Arrays.stream(data).flatMapToInt(row ->Arrays.stream(row));
		System.out.println(is1.sum());
		
		int[] l1 = {4,8,9};
		IntDemoPerson p1 = new IntDemoPerson("Ram", l1);
		int[] l2 = {2,7,8};
		IntDemoPerson p2 = new IntDemoPerson("Shyam", l2);		
		List<IntDemoPerson> list = Arrays.asList(p1,p2);
		IntStream is2 = list.stream().flatMapToInt(row -> Arrays.stream(row.getLuckyNum()));
		System.out.println(is2.max().getAsInt());
	}
}

class IntDemoPerson {
	private String name;
	private int[] luckyNum;
	public IntDemoPerson(String name, int[] luckyNum){
		this.name = name;
		this.luckyNum = luckyNum;
	}
	public String getName() {
		return name;
	}
	public int[] getLuckyNum() {
		return luckyNum;
	}
} 	

