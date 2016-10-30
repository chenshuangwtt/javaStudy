package com.cs.test.week10;

import java.io.File;
import java.io.FileInputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class IOTest {  
    static final int BUFFER_SIZE = 1024;  
    public static void main(String[] args) throws Exception {  
        File file = new File("D:\\salary.txt");  
        FileInputStream in = new FileInputStream(file);  
        FileChannel channel = in.getChannel();  
        MappedByteBuffer buff = channel.map(FileChannel.MapMode.READ_ONLY, 0,  
                channel.size());  
        byte[] b = new byte[1024];  
        int len = (int) file.length();  
        long begin = System.currentTimeMillis();  
        for (int offset = 0; offset < len; offset += 1024) {  
            if (len - offset > BUFFER_SIZE) {  
                buff.get(b);  
            } else {  
                buff.get(new byte[len - offset]);  
            }  
        }  
        long end = System.currentTimeMillis();  
        System.out.println("time is:" + (end - begin));  
    }  
}  