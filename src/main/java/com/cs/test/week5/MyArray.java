package com.cs.test.week5;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

public class MyArray {
	private byte[] data;
	private int curPos; // 当前有效的数据位置，比如curPos=5，表示从0-5都是有值的，可以读取，追加写入的时候，也从这里开始

	private static Unsafe getUnsafe() {
		try {
			Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
			theUnsafe.setAccessible(true);
			return (Unsafe) theUnsafe.get(null);
		} catch (Exception e) {
			throw new AssertionError(e);
		}
	}
	public MyArray(int size) {
		this.curPos = 0;
		data = new byte[size];
	}

	public String get() {
		String result = "";
		getUnsafe().loadFence();
		for (int i = 0; i < curPos; i++) {
			result += " " + data[i];
		}
		return result + "  length = " + curPos;
	}

	public void set(byte b, int pos) {
		data[pos] = b;
		getUnsafe().fullFence();
		System.out.println("modified position" + pos + " value is " + b);
	}

	public void append(byte b) {
		getUnsafe().loadFence();
		data[curPos] = b;
		curPos++;
		getUnsafe().fullFence();
		System.out.println("append data value is " + b);
	}

	public int getCurPos() {
		getUnsafe().loadFence();
		System.out.println("get curpos " + curPos);
		return curPos;
	}

	public void setCurPos(int pos) {
		curPos = pos;
		getUnsafe().fullFence();
	}
}
