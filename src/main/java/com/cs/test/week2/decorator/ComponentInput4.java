package com.cs.test.week2.decorator;

/**
 * 字符串转小写
 */
public class ComponentInput4  extends Decorator {

	public ComponentInput4(ClientInput clientInput) {
		super(clientInput);
	}
	
	@Override
	public String doWriting(String input) {
		input = super.doWriting(input);
		return  lowerInput(input);
	}

	public String lowerInput(String input){
		return  input.toLowerCase();
	}
}
