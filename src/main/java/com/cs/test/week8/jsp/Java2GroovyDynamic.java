package com.cs.test.week8.jsp;

import java.io.File;
import java.io.IOException;

import org.codehaus.groovy.control.CompilationFailedException;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

public class Java2GroovyDynamic {
	public static void main(String[] args) throws CompilationFailedException, IOException, InstantiationException, IllegalAccessException {
		ClassLoader parent = ClassLoader.getSystemClassLoader();
		GroovyClassLoader  loader = new GroovyClassLoader(parent);
		Class groovyClass = loader.parseClass(new File(System.getProperty("user.dir")+("/src/main/resources/GroovyDemo.groovy")));
		GroovyObject  groovyObject = (GroovyObject) groovyClass.newInstance();
		Object [] param = {123,321};
		int res = (int) groovyObject.invokeMethod("add", param);
		System.out.println("res="+res);
		
		
		
		
	}
}
