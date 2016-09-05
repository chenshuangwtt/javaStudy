package com.cs.test.week4;

import java.util.StringJoiner;

/**
 * 
 * StringJoiner(CharSequence d, CharSequence p, CharSequence s) This constructor
 * also takes prefix and suffix to add. Prefix and suffix does not depend on the
 * number of added element.
 * 
 * StringJoiner.merge(StringJoiner other) We can merge two StringJoiner. There
 * will be a primary StringJoiner to which another StringJoiner will be added.
 * Another StringJoiner will not bring its prefix and sufiix while being added
 * in primary StringJoiner.
 * 
 * StringJoiner.length() StringJoiner.length() gets the length as normal string
 * length method
 * 
 * @author Administrator
 *
 */
public class StringJoinerDemoTwo {
	public static void main(String[] args) {
		StringJoiner sjObj = new StringJoiner(",", "{", "}");
		// Add Element
		sjObj.add("AA").add("BB").add("CC").add("DD").add("EE");
		String output = sjObj.toString();
		System.out.println(output);
		// Create another StringJoiner
		StringJoiner otherSj = new StringJoiner(":", "(", ")");
		otherSj.add("10").add("20").add("30");
		System.out.println(otherSj);
		// Use StringJoiner.merge(StringJoiner o)
		StringJoiner finalSj = sjObj.merge(otherSj);
		System.out.println(finalSj);
		// get length using StringJoiner.length()
		System.out.println("Length of Final String:" + finalSj.length());

	}
}
