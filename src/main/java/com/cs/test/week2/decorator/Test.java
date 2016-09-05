package com.cs.test.week2.decorator;

import java.util.Scanner;

public class Test {
	public static void main(String[] args) throws Exception {
		System.out.println("请输入字符串："); 
		Scanner input = new Scanner(System.in);
		String str = input.nextLine();
		System.out.println("1:加密\n2:反转字符串\n3:转成大写\n4:转成小写\n5:扩展或者剪裁到10个字符，不足部分用!补充");
		ClientInput clientInput = new RealComponent();
		System.out.println("请输入需要的操作,多个逗号分开："); 
		String[] type = input.nextLine().split(",");
		for(int i = 0;i < type.length;i++){	//反射实现
			Class<Decorator> clazz = (Class<Decorator>)Class.forName("com.cs.test.decorator.ComponentInput"+type[i]);
			clientInput = clazz.getDeclaredConstructor(ClientInput.class).newInstance(clientInput);
		}
		System.out.println(clientInput.doWriting(str));
		input.close();
	}
}
