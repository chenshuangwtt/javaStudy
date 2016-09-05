package com.cs.test.week4.static2;

/**
 * Scenario Two: Static Method in Interface Now we can write static method in
 * interface too. In our Village interface, I have declared getVillageId() as an
 * static method. This static method can be accessed in default method as well.
 * 
 */
public interface Village {
	void setNumOfPeople(int num);

	void setName(String name);

	static int getVillageId() {
		return 1;
	}

	default String getBusinessType() {
		return "Business type is Farming  and village id:" + getVillageId();
	}
}