package com.cs.test.week4.static3;

import com.cs.test.week4.static2.Village;

public class Main {
	public static void main(String[] args) {
		Location lo = new Location();
		System.out.println(lo.getBusinessType());
		System.out.println("Village id:" + Village.getVillageId());
		System.out.println("Location Id:" + lo.getLocationId());
	}
}