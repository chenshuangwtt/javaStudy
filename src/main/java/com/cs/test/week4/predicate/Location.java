package com.cs.test.week4.predicate;

public class Location implements Village {
	public int noOfPeople;
	public String name;

	@Override
	public void setNumOfPeople(int n) {
		this.noOfPeople = n;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}
}