package com.cs.test.week4.runnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Lambda Expression Syntax Lambda Expression Syntax is very easy to read and
 * understand. Lambda Expression Syntax will look like (Argument part) -> Body
 * part Find some example below. 
 * Sample 1: If method takes no argument and
 * printing the message. () -> System.out.println("Your message");
 * 
 * Sample 2 : If
 * method takes two arguments and do some business logic and returns the value.
 * (int a, int b) -> a+b; The value of a+b will be returned by the method.
 * 
 * Sample 3: If method takes one argument and do some business logic (String s)
 * -> s + "Hello World"; String will be returned after concatenation
 * 
 * Example 1: Using Runnable with Lambda Expression In the below example we are
 * running a Runnable thread. Before lambda expression, to achieve runnable
 * thread, we need to define a class which implements Runnable interface to get
 * runnable object. Now see how to achieve using lambda expressions the same
 * object
 * 
 */
public class RunnableDemo {
	public static void main(String[] args) {
		final ExecutorService exService = Executors.newSingleThreadExecutor();
		Runnable r = () -> System.out.println("Lambda Expression Test with Runnable");
		exService.execute(r);
	}
}