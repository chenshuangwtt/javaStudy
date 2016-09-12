package com.cs.test.week5;

import java.lang.reflect.Field;
import java.util.Random;

import sun.misc.Unsafe;

public class MyArray2 {
	private static byte[] data = new byte[11];
	private static int curPos = 0;		//当前有效的数据位置，比如curPos=5，表示从0-5都是有值的，可以读取，追加写入的时候，也从这里开始

	public static void main(String[] args) {
		final  int THREAD_NUM = 10;
		Random random = new Random();
		appendData((byte)random.nextInt());
		new Thread(){
			public void run(){
				while(true){
					if(curPos<10){
						appendData((byte)random.nextInt());
					}
					setData((byte)random.nextInt(), curPos);
				}
			}
		}.start();
		for(int i =0;i<THREAD_NUM;i++){
			new Thread(Integer.toString(i)){
				public void run(){
					while(true){
						getData(Integer.parseInt(getName()));
					}
				}
			}.start();
		}
	}
	
	private static Unsafe getUnsafe() {
		try {
			Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
			theUnsafe.setAccessible(true);
			return (Unsafe) theUnsafe.get(null);
		} catch (Exception e) {
			throw new AssertionError(e);
		}
	}
	
	
	public static void getData(int threadNum){
		getUnsafe().loadFence();
		for(int i =0;i<MyArray2.curPos;i++){
			System.out.println(" "+MyArray2.data[i]);
		}
	}
	
	
	public static void setData(byte b ,int pos){
		MyArray2.data[pos] = b;
		getUnsafe().fullFence();
		System.out.println("modified position"+ pos +" value is "+b);
	}
	
	public static void appendData(byte b){
		getUnsafe().loadFence();
		MyArray2.data[MyArray2.curPos+1] =b;
		MyArray2.curPos++;
		getUnsafe().fullFence();
	}
	
	public static int getCurPos(){
		getUnsafe().loadFence();
		return  curPos;
	}
	
	public static void setCurPos(int curPos){
		MyArray2.curPos = curPos;
		getUnsafe().fullFence();
	}
	
	
}	
