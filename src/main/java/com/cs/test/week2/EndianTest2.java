package com.cs.test.week2;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class EndianTest2 {
	public static long bigToLittleEndian(long bigendian) {
	    ByteBuffer buf = ByteBuffer.allocate(8);
	    buf.order(ByteOrder.BIG_ENDIAN);
	    buf.putLong(bigendian);
	    buf.order(ByteOrder.LITTLE_ENDIAN);
	    return buf.getLong(0);
	}
	 
	public static void main(String[] args) {
		System.out.println(Long.toHexString(0x40429C28F5C28F5CL));
	    long res = bigToLittleEndian(0x40429C28F5C28F5CL);
	    System.out.println(Long.toHexString(res));
	}
}