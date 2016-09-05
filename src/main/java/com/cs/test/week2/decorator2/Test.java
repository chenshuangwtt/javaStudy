package com.cs.test.week2.decorator2;

import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		System.out.println("请输入字符串："); 
		Scanner input = new Scanner(System.in);
		String str = input.nextLine();
		System.out.println("1:加密\n2:反转字符串\n3:转成大写\n4:转成小写\n5:扩展或者剪裁到10个字符，不足部分用!补充");
		ClientInput clientInput = new RealComponent();
		System.out.println("请输入需要的操作,多个逗号分开："); 
		String[] type = input.nextLine().split(",");
		for(int i = 0;i < type.length;i++){
			switch (type[i]) {
			case "1":
				clientInput = new EncryptInput(clientInput);
				break;
			case "2":
				clientInput = new ReverseInput(clientInput);
				break;	
			case "3":
				clientInput = new UpperInput(clientInput);
				break;
			case "4":
				clientInput = new LowerInput(clientInput);
				break;
			case "5":
				clientInput = new CutOrFillInput(clientInput);
			default:
				break;
			}
		}
		System.out.println(clientInput.doWriting(str));
		input.close();
	}
}
