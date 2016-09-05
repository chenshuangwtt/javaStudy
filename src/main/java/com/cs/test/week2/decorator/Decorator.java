package com.cs.test.week2.decorator;

/**
 * 所有装饰器的父类，需要定义一个与组件接口一致的接口
 */
public class Decorator implements ClientInput {
	private ClientInput clientInput;

	public Decorator(ClientInput clientInput) {
		this.clientInput = clientInput;
	}

	public String doWriting(String input) {
		return clientInput.doWriting(input);
	}
}
