package com.cs.test.week2.decorator2;

//组件对象的接口，可以给这些对象动态的添加职责
public interface ClientInput {
	 /** 
     * 输出文字
     */ 
    String doWriting(String input); 
}
