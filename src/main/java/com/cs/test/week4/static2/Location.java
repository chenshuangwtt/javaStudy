package com.cs.test.week4.static2;

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

	public int getLocationId() {
		return Village.getVillageId();
	}
}