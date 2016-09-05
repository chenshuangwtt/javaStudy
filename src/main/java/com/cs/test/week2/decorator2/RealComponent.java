package com.cs.test.week2.decorator2;

/**
 * 具体的组件对象，实现了组件接口。该对象通常就是被装饰器装饰的原始对象，可以给这个对象添加职责；
 */
public class RealComponent implements ClientInput {
	
	public String doWriting(String input) {
		return input;
	}
}
