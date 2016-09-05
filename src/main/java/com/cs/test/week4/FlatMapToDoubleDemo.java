package com.cs.test.week4;

import java.util.Arrays;
import java.util.List;
import java.util.stream.DoubleStream;

public class FlatMapToDoubleDemo {
	public static void main(String[] args) {
		double[][] data = { { 1.5, 2.4 }, { 3.2, 4.4 }, { 5.2, 6.8 } };
		DoubleStream ds1 = Arrays.stream(data).flatMapToDouble(row -> Arrays.stream(row));
		System.out.println(ds1.average().getAsDouble());
		double[] d1 = { 60.5, 58.9, 62.5 };
		DoubleDemoPerson p1 = new DoubleDemoPerson("Ram", d1);
		double[] d2 = { 70.5, 65.3, 67.4 };
		DoubleDemoPerson p2 = new DoubleDemoPerson("Shyam", d2);
		List<DoubleDemoPerson> list = Arrays.asList(p1, p2);
		DoubleStream ds2 = list.stream().flatMapToDouble(row -> Arrays.stream(row.getWeightsInAYear()));
		System.out.println(ds2.min().getAsDouble());
	}
}

class DoubleDemoPerson {
	private String name;
	private double[] weightsInAYear;

	public DoubleDemoPerson(String name, double[] weightsInAYear) {
		this.name = name;
		this.weightsInAYear = weightsInAYear;
	}

	public String getName() {
		return name;
	}

	public double[] getWeightsInAYear() {
		return weightsInAYear;
	}
}
