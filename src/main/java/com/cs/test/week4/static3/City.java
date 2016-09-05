package com.cs.test.week4.static3;

public interface City {
	void setName(String name);

	void setArea(int area);

	default String getBusinessType() {
		return "Service";
	}
}