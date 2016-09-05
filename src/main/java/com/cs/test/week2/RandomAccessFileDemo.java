package com.cs.test.week2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileDemo {
      // 所有的异常直接抛出，程序中不再进行处理
      public static void main(String args[]) throws Exception {
           write();
           read();
      }

	public static void write() throws FileNotFoundException, IOException {
		   File f = new File("d:" + File.separator + "test.txt"); // 指定要操作的文件
           RandomAccessFile rdf = null; // 声明RandomAccessFile类的对象
           rdf = new RandomAccessFile(f, "rw");// 读写模式，如果文件不存在，会自动创建
           String name = null;
           int age = 0;
           name = "zhangsan"; // 字符串长度为8
           age = 30; // 数字的长度为4
           rdf.writeBytes(name); // 将姓名写入文件之中
           rdf.writeInt(age); // 将年龄写入文件之中
           name = "lisi    "; // 字符串长度为8
           age = 31; // 数字的长度为4
           rdf.writeBytes(name); // 将姓名写入文件之中
           rdf.writeInt(age); // 将年龄写入文件之中
           name = "wangwu  "; // 字符串长度为8
           age = 32; // 数字的长度为4
           rdf.writeBytes(name); // 将姓名写入文件之中
           rdf.writeInt(age); // 将年龄写入文件之中
           rdf.close(); // 关闭
	}
	
	public static void read() throws FileNotFoundException, IOException {
		  File f = new File("d:" + File.separator + "test.txt") ;    // 指定要操作的文件
	        RandomAccessFile rdf = null ;        // 声明RandomAccessFile类的对象
	        rdf = new RandomAccessFile(f,"r") ;// 以只读的方式打开文件
	        String name = null ;
	        int age = 0 ;
	        byte b[] = new byte[8] ;    // 开辟byte数组
	        // 读取第二个人的信息，意味着要空出第一个人的信息
	        rdf.skipBytes(12) ;        // 跳过第一个人的信息
	        for(int i=0;i<b.length;i++){
	            b[i] = rdf.readByte() ;    // 读取一个字节
	        }
	        name = new String(b) ;    // 将读取出来的byte数组变为字符串
	        age = rdf.readInt() ;    // 读取数字
	        System.out.println("第二个人的信息 --> 姓名：" + name + "；年龄：" + age) ;
	        // 读取第一个人的信息
	        rdf.seek(0) ;    // 指针回到文件的开头
	        for(int i=0;i<b.length;i++){
	            b[i] = rdf.readByte() ;    // 读取一个字节
	        }
	        name = new String(b) ;    // 将读取出来的byte数组变为字符串
	        age = rdf.readInt() ;    // 读取数字
	        System.out.println("第一个人的信息 --> 姓名：" + name + "；年龄：" + age) ;
	        rdf.skipBytes(12) ;    // 空出第二个人的信息
	        for(int i=0;i<b.length;i++){
	            b[i] = rdf.readByte() ;    // 读取一个字节
	        }
	        name = new String(b) ;    // 将读取出来的byte数组变为字符串
	        age = rdf.readInt() ;    // 读取数字
	        System.out.println("第三个人的信息 --> 姓名：" + name + "；年龄：" + age) ;
	        rdf.close() ;                // 关闭
	}
};