package com.cs.test.week4.predicate;

/**
 * Default Method in Interface 
 * To understand using default method, I am creating
 * an interface Village that has some method declarations and one default
 * method. Default method starts with default keyword. By default all methods of
 * interface will be public, so no need to use public keyword to declare and
 * define methods in interface.
 * 
 * @author Administrator
 *
 */
public interface Village {
	void setNumOfPeople(int num);

	void setName(String name);

	default String getBusinessType() {
		return "Most of the Village people do Farming";
	}
}