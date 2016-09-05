package com.cs.test.week3;
import java.util.ArrayDeque;
import java.util.Deque;
/**
 * 使用 Deque 实现堆栈
 */
public class IntegerStack {
  private Deque<Integer> data = new ArrayDeque<Integer>();
 
  public void push(Integer element) {
    data.addFirst(element);
  }
 
  public Integer pop() {
    return data.removeFirst();
  }
 
  public Integer peek() {
    return data.peekFirst();
  }
 
  public String toString() {
    return data.toString();
  }
 
  public static void main(String[] args) {
    IntegerStack stack = new IntegerStack();
    for (int i = 0; i < 5; i++) {
      stack.push(i);
    }
    System.out.println("After pushing 5 elements: " + stack);
    int m = stack.pop();
    System.out.println("Popped element = " + m);
    System.out.println("After popping 1 element : " + stack);
    int n = stack.peek();
    System.out.println("Peeked element = " + n);
    System.out.println("After peeking 1 element : " + stack);
  }
}
/* 输出
After pushing 5 elements: [4, 3, 2, 1, 0]
Popped element = 4
After popping 1 element : [3, 2, 1, 0]
Peeked element = 3
After peeking 1 element : [3, 2, 1, 0]
*/