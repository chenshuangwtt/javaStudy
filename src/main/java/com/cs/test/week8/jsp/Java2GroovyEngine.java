package com.cs.test.week8.jsp;

import java.io.IOException;

import groovy.lang.Binding;
import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import groovy.util.ScriptException;

public class Java2GroovyEngine {
	public static void main(String[] args) throws IOException, ResourceException, ScriptException {
		GroovyScriptEngine groovyScriptEngine = new GroovyScriptEngine("");
		Binding binding = new Binding();
		binding.setVariable("input", "Groovy");
		groovyScriptEngine.run("Test.groovy", binding);
		System.out.println(binding.getVariable("output"));
	}
}
