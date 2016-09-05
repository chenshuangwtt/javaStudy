package com.cs.test.week2.decorator;

/**
 *	扩展或者剪裁到10个字符，不足部分用！补充 
 */
public class ComponentInput5 extends Decorator {

	public ComponentInput5(ClientInput clientInput) {
		super(clientInput);
	}

	@Override
	public String doWriting(String input) {
		input = super.doWriting(input);
		return cutOrFillInput(input);
	}

	private String cutOrFillInput(String input) {
		int length = input.length();
		if (input.length() >= 10) {
			input = input.substring(0, 10);
		} else {
			for (int i = 0; i < 10 - length; i++) {
				input = input.concat("!");
			}
		}
		return input;
	}

	public static void main(String[] args) {
		String input = "abcd";
		int length = input.length();
		if (input.length() >= 10) {
			input = input.substring(0, 10);
		} else {
			for (int i = 0; i < 10 - length; i++) {
				input = input.concat("!");
			}
		}
		System.err.println(input);
	}
}
