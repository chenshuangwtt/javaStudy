package com.cs.test.week2.decorator2;

/**
 * 字符串转大写
 */
public class UpperInput extends Decorator {
	public UpperInput(ClientInput clientInput) {
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
