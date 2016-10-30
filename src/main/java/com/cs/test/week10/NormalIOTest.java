package com.cs.test.week10;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class NormalIOTest {  
    static final int BUFFER_SIZE = 1024;  
    public static void main(String[] args) throws Exception {  
    	  File file = new File("D:\\salary.txt");  
    	  FileInputStream in = new FileInputStream(file);  
    	  FileChannel channel = in.getChannel();  
    	  ByteBuffer buff = ByteBuffer.allocate(1024);   
    	  long begin = System.currentTimeMillis();  
    	  while (channel.read(buff) != -1) {  
    	      buff.flip();  
    	      buff.clear();  
    	  }  
    	  long end = System.currentTimeMillis();  
    	  System.out.println("time is:" + (end - begin));   
    }  
}  