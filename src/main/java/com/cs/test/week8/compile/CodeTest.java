package com.cs.test.week8.compile;

import java.io.BufferedReader;

public class CodeTest  {   
      
    //文件读取路径  
    private static final String READER_PATH = System.getProperty("user.dir")+"/codeFile/codeText.txt";  
    //文件输出路径  
    private static final String WRITER_PATH = System.getProperty("user.dir")+"/src/com/code/java/CodeText.java";  
    //包路径  
    private static final String PACK_PATH = "com.code.java.CodeText";  
      
    public static void main(String[] args) {  
        CodeGenerate code = new CodeGenerate();  
        //读文本文件  
        BufferedReader br = code.fileReader(READER_PATH);     
        //生成java类  
        code.fileWriter(br, WRITER_PATH);                     
        //编译java类  
        code.javac(WRITER_PATH);                              
        //运行java类  
        code.java(PACK_PATH);                                 
    }  
};  