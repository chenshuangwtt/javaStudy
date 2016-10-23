package com.cs.test.week8.jsp;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class Test {  
  
    /** 
     * @param args 
     * @throws IOException  
     * @throws IllegalAccessException  
     * @throws InstantiationException  
     * @throws ClassNotFoundException  
     */  
    public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {  
        // TODO Auto-generated method stub  
        JavaCompiler complier = ToolProvider.getSystemJavaCompiler();     
        StandardJavaFileManager sjf =   
                complier.getStandardFileManager(null, null, null);  
        Iterable it = sjf.getJavaFileObjects("D:\\hello.java");    
        CompilationTask task = complier.getTask(null, sjf, null, null, null, it);  
        task.call();  //调用创建  
        sjf.close();  
          
        URL urls[] = new URL[]{ new URL("file:/D:/")};  //储存文件目录的地址  
        URLClassLoader uLoad = new URLClassLoader(urls);  //classloader从哪个目录找？  
        Class c = uLoad.loadClass("hello");  //找哪个class文件 注意不带后缀名  
        c.newInstance();  //创建一个实例  
    }  
    
}  