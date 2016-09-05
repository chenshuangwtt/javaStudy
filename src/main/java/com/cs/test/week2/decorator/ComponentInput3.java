package com.cs.test.week2.decorator;

/**
 * 字符串转大写
 */
public class ComponentInput3 extends Decorator {
	public ComponentInput3(ClientInput clientInput) {
		super(clientInput);
	}
	public String doWriting(String input) {
		input = super.doWriting(input);
		return upperInput(input);
	}
	public String upperInput(String input){
		return input.toUpperCase();
	}
}
