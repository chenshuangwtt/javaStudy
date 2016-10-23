package com.cs.test.week8.jsp;

import java.io.IOException;
import java.net.URI;

import javax.tools.SimpleJavaFileObject;

class StringObject extends SimpleJavaFileObject { 
    private String contents = null;
    public StringObject(String className, String contents) throws Exception { 
        super(URI.create("string:///" + className.replace('.', '/') 
                + Kind.SOURCE.extension), Kind.SOURCE); 
        this.contents = contents; 
    }
    public CharSequence getCharContent(boolean ignoreEncodingErrors) 
            throws IOException { 
        return contents; 
    } 
}