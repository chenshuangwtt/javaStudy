package com.cs.test.week1;

import java.lang.reflect.Field;
import java.util.Arrays;

import sun.misc.Unsafe;

public class Test {
    private static int byteArrayBaseOffset;

    public static void main(String[] args) throws SecurityException,
            NoSuchFieldException, IllegalArgumentException,
            IllegalAccessException {
//        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
//        theUnsafe.setAccessible(true);
//        Unsafe UNSAFE = (Unsafe) theUnsafe.get(null);
//        System.out.println(UNSAFE);
//
//        byte[] data = new byte[10];
//        System.out.println(Arrays.toString(data));
//        byteArrayBaseOffset = UNSAFE.arrayBaseOffset(byte[].class);
//
//        System.out.println(byteArrayBaseOffset);
//        UNSAFE.putByte(data, byteArrayBaseOffset, (byte) 1);
//        UNSAFE.putByte(data, byteArrayBaseOffset + 5, (byte) 5);
//        System.out.println(Arrays.toString(data));
    	
    	
//    	 int score[] = {67, 69, 75, 87, 89, 90, 99, 100};
//         for (int i = 0; i < score.length -1; i++){    //最多做n-1趟排序
//             for(int j = 0 ;j < score.length - i - 1; j++){    //对当前无序区间score[0......length-i-1]进行排序(j的范围很关键，这个范围是在逐步缩小的)
//                 if(score[j] < score[j + 1]){    //把小的值交换到后面
//                     int temp = score[j];
//                     score[j] = score[j + 1];
//                     score[j + 1] = temp;
//                 }
//             }            
//             System.out.print("第" + (i + 1) + "次排序结果：");
//             for(int a = 0; a < score.length; a++){
//                 System.out.print(score[a] + "\t");
//             }
//             System.out.println("");
//         }
//             System.out.print("最终排序结果：");
//             for(int a = 0; a < score.length; a++){
//                 System.out.print(score[a] + "\t");
//        }

    	byte a = 1;
    	int b  = a>>13;
    	int c = a<<13;
    	System.out.println(b);
    	System.out.println(c);
    }
}