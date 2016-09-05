package com.cs.test.week2;
public class EndianTest {  
    public static void main(String[] args){  
        short x=257;//00000001 00000001 （2的8次方+1）  
        short x2 = BigEndian2LittleEndian16(x);  
        System.out.println("x="+x+",x2="+x2);  
          
        int y= 1024;  //00000000 00000000 00000001 00000001 （2的8次方+1）  
        int y2 = BigEndian2LittleEndian32(y);  
        System.out.println("y="+y+",y2="+y2);  
    }  
      
    /** 
     * convert big-endian to little-endian  
     * */  
    private static final short BigEndian2LittleEndian16(short x) {  
        return (short) ((x & 0xFF) << 8 | 0xFF & (x >> 8));  
    }  
  
    /** 
     * convert big-edian to little-edian  
     * */  
    private static final int BigEndian2LittleEndian32(int x) {  
        return (x & 0xFF) << 24 | (0xFF & x >> 8) << 16 | (0xFF & x >> 16) << 8  
                | (0xFF & x >> 24);  
    }  
}  