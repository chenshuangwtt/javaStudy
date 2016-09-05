package com.cs.test.week2;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BigEndianTest {
	public static void main(String[] args) throws IOException {
		int num = 10240;
		byte[] bytes = int2byte(num); 
		File file = new File("D:\\Bigendian.txt");
		FileWriter fileWriter = new FileWriter(file);
		fileWriter.write(bytes[0]);
		fileWriter.write(bytes[1]);
		fileWriter.write(bytes[2]);
		fileWriter.write(bytes[3]);
		fileWriter.flush();
		fileWriter.close();
		System.out.println("大头模式:"+Integer.toHexString(num));
		FileReader fileReader = new FileReader(file);
		int length =0;
		while((length = fileReader.read())!=-1){
			System.out.println("result is "+Integer.toHexString(length));
		}
		fileReader.close();
	}

	public static byte[] int2byte(int res) {
		byte[] targets = new byte[4];
		targets[0] = (byte) (res & 0xff);// 最低位
		targets[1] = (byte) ((res >> 8) & 0xff);// 次低位
		targets[2] = (byte) ((res >> 16) & 0xff);// 次高位
		targets[3] = (byte) (res >>> 24);// 最高位,无符号右移。
		return targets;
	}

}