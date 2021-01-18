package com.coding.interview.guide.stackandqueue;

import org.junit.Test;
import java.util.Stack;

/**
 * @Author 汪玉龙
 * @title 设计一个有getMin功能的栈 包含push pop getMin三个功能时间复杂度O(1)
 * @Date 2021/1/18 21:12
 */
public class MyStack1 {
        // 数据栈
        private final Stack<Integer> dataStack;
       //  存放最小值的栈
        private final Stack<Integer> minStack;

        public MyStack1() {
               this.dataStack = new Stack<Integer>();
               this.minStack = new Stack<Integer>();
        }

        /**
         * 入栈操作
         * @param data 入栈数据
         */
        public void push(int data) {
               dataStack.push(data);
               if (minStack.isEmpty()) {
                    minStack.push(data);
                    return;
               }
               if (data <= minStack.peek()) {
                   minStack.push(data);
               }
        }

        /**
         * 出栈操作
         * @return 出栈元素
         */
        public int pop() {
            if (dataStack.isEmpty()) {
                throw new RuntimeException("栈为空,无法出栈");
            }
                Integer data = dataStack.pop();
                if (data.equals(minStack.peek()) ) {
                        minStack.pop();
                }
                return data;
        }

        /**
         * 获取栈中最小值
         * @return 栈中最小值
         */
        public int getMin() {
            if (dataStack.isEmpty()) {
                throw new RuntimeException("栈为空,无法获取最小值");
            }
            return minStack.peek();
        }

        @Test
        public void test1() {
            MyStack1 stack1 = new MyStack1();
            stack1.push(4);
            stack1.push(6);
            stack1.push(5);
            stack1.push(4);
            stack1.push(7);
            System.out.println(stack1.getMin());
            System.out.println(stack1.pop());
            System.out.println(stack1.getMin());
            System.out.println(stack1.pop());
            System.out.println(stack1.getMin());
            System.out.println(stack1.pop());
            System.out.println(stack1.getMin());
            System.out.println(stack1.pop());
            System.out.println(stack1.getMin());
            System.out.println(stack1.pop());
            //System.out.println(stack1.getMin());
            System.out.println(stack1.pop());
        }
}
