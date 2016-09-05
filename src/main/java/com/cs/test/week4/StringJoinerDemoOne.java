package com.cs.test.week4;

import java.util.StringJoiner;

/**
 * java.util.StringJoiner has been introduced in Java 8. StringJoiner is a util
 * method to construct a string with desired delimiter. If required we can also
 * add prefix and suffix to the final string. To achieve it, StringJoiner is
 * created with two constructor, first is only with delimiter and second has
 * delimiter, prefix and suffix. We can merge two StringJoiner. Find the
 * description and example. 
 * 
 * StringJoiner(CharSequence d) This constructor uses a
 * delimiter to separate the added element. 
 * 
 * StringJoiner.add(CharSequence
 * element) StringJoiner.add method adds element to it. We need to call add
 * method every time as the number of elements to be added
 * 
 * @author Administrator
 *
 */
public class StringJoinerDemoOne {
	public static void main(String[] args) {
		StringJoiner sj = new StringJoiner("-");
		sj.add("Ram");
		System.out.println(sj);
		sj.add("Shyam").add("Mohan");
		System.out.println(sj);
	}
}
