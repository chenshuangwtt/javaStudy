package com.cs.test.week2.decorator2;

/**
 * 字符串转小写
 */
public class LowerInput  extends Decorator {

	public LowerInput(ClientInput clientInput) {
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
