package com.cs.test.week10.buffer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class DirectBuffer {
	public static void main(String[] args) throws IOException {
		String infile = "D:\\test.txt";  
        FileInputStream fin = new FileInputStream( infile );  
        FileChannel fcin = fin.getChannel();  
        String outfile = String.format("D:\\testcopy.txt");  
        FileOutputStream fout = new FileOutputStream( outfile );      
        FileChannel fcout = fout.getChannel();  
        // 使用allocateDirect，而不是allocate  
        ByteBuffer buffer = ByteBuffer.allocateDirect( 1024 );  
        while (true) {  
            buffer.clear();  
            int r = fcin.read( buffer );  
            if (r==-1) {  
                break;  
            }  
            buffer.flip();  
            fcout.write( buffer );  
        }  
	}
}
