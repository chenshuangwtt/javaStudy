package com.cs.test.week2;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class TestCPU {
  public static void main(String[] args) {
	//判断本地是大头模式 还是小头模式
    if (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN) {
      System.out.println("BIG_ENDIAN");
    } else {
      System.out.println("LITTLE_ENDIAN");
    }
    //小头模式显示
    ByteBuffer buffer = ByteBuffer.allocate(4);
	buffer.order(ByteOrder.LITTLE_ENDIAN);
	buffer.asIntBuffer().put(10240);
	System.out.println(Arrays.toString(buffer.array()));
	buffer.rewind();
	//大头模式显示
	buffer.order(ByteOrder.BIG_ENDIAN);
	buffer.asIntBuffer().put(10240);
	System.out.println(Arrays.toString(buffer.array()));
  }
}