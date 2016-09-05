package com.cs.test.week2;

import java.io.Serializable;
import java.util.Random;

public class Salary implements Comparable<Salary>,Serializable{
	private String name;	
	private int baseSalary;	
	private int bouns;
	//baseSalary*13+this.bouns 冒泡排序
	//构造函数
	public Salary(){
		//baseSalary范围是5-100万，bonus为（0-10万），其中name长度为5，随机字符串，
		this.name = getRandomString(6);
		this.bouns = randomBouns();
		this.baseSalary = randomBaseSalary();
	}
	
	public Salary(String name, int baseSalary, int bouns) {
		super();
		this.name = name;
		this.baseSalary = baseSalary;
		this.bouns = bouns;
	}
	
	//随机6位字符串
	public String randomName(){
		String result="";  
        for(int i=0;i<6;i++){  
            int intVal=(int)(Math.random()*26+97);  
            result=result+(char)intVal;  
        }  
        return result;
	}
	
	public  String getRandomString(int length) { //length表示生成字符串的长度
	    String base = "abcdefghijklmnopqrstuvwxyzQWERTYUIOPASDFGHJKLZXCVBNM0123456789";   
	    Random random = new Random();   
	    StringBuffer sb = new StringBuffer();   
	    for (int i = 0; i < length; i++) {   
	        int number = random.nextInt(base.length());   
	        sb.append(base.charAt(number));   
	    }   
	    return sb.toString();   
	 }  
	
	//1-10范围
	public int randomBouns(){
		return new Random().nextInt(10) + 1;
	}
	//5-100
	public int randomBaseSalary(){
		return (int)(Math.random()*96)+5;
	}
	//获取总数
	public int totalSalary(){
		return this.baseSalary*13+this.bouns;
	}
	//写数据
	public String write(){
		return name +","+baseSalary+","+bouns;
	}
	//baseSalary*13+bonus
	public int compareTo(Salary o) {
		if(this.totalSalary() > o.totalSalary()){
			return -1; //由底到高排序
		}else if(this.totalSalary() < o.totalSalary()){
			return 1;
		}else{
			return 0;
		}
	}

	@Override
	public String toString() {
		return "Salary [name=" + name + ", baseSalary=" + baseSalary + ", bouns=" + bouns + "]   Sum ="+(baseSalary*13+bouns) ;
	}
	
	public String print(){
		return name +": 年薪"+this.totalSalary() +"万";
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(int baseSalary) {
		this.baseSalary = baseSalary;
	}

	public int getBouns() {
		return bouns;
	}

	public void setBouns(int bouns) {
		this.bouns = bouns;
	}
}
