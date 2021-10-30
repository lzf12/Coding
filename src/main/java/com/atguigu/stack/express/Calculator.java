package com.atguigu.stack.express;

import lombok.Data;


/**
 * @Author 汪玉龙
 * @title  计算一个表达式
 * @Date 2021/4/5 14:21
 */
public class Calculator {

    public static void main(String[] args) {
        // 计算表达式的值
        String express = "100+20*6-80";
        // 定义符号栈和数栈
        ArrayStack2 numberStack = new ArrayStack2(10);
        ArrayStack2 symbolStack = new ArrayStack2(10);
        // 定义扫描变量
        int index = 0;
        char ch = ' ';
        // 记录数栈pop出第一个数
        int num1 = 0;
        // 记录数栈pop出第二个数
        int num2 = 0;
        int oper = 0;
        StringBuffer splicingStr = new StringBuffer();
        while (true) {
            // 依次得到表达式里面的每一个字符
            ch = express.substring(index, index + 1).charAt(0);
            // 判断ch是什么然后做相应的处理
            if (symbolStack.isOper(ch)) {
                // 符号栈不为空的话, 判断符号的优先级是否高于符号栈里面的栈顶元素
                if (!symbolStack.isEmpty()) {
                    if (symbolStack.getPriority(ch) <= symbolStack.getPriority(symbolStack.peek())) {
                         // 如果优先级小于等于栈里面的栈顶元素, 那么直接从数栈弹出两个数然后进行运算
                         oper = symbolStack.pop();
                         num1 = numberStack.pop();
                         num2 = numberStack.pop();
                        // 进行运算
                        int result = symbolStack.operation(oper, num1, num2);
                        // 将结果入数栈
                        numberStack.push(result);
                        // 将符号入符号栈
                        symbolStack.push(ch);
                    } else {
                        // 优先级大于符号栈栈顶那么直接入符号栈
                        symbolStack.push(ch);
                    }
                } else {
                    // 符号栈为空直接入栈
                    symbolStack.push(ch);
                }
            } else {
                // 字符和int差48,所以字符-48就得到对应的数字 '7' - 48 = 7
                // numberStack.push(ch - 48);
                // 不是符号需要处理是否是多位数情况
                splicingStr.append(ch);

                if (index == express.length() - 1) {
                    // 如果已经是最后一个数字直接入栈
                    numberStack.push(Integer.parseInt(splicingStr.toString()));
                } else {
                    // 向后面再探测一位判断
                    if (symbolStack.isOper(express.substring(index + 1, index + 2).charAt(0))) {
                        numberStack.push(Integer.parseInt(splicingStr.toString()));
                        // 多位数入栈之后需要清空,用来处理下一个多位数
                        splicingStr.delete(0, splicingStr.length());
                    }
                }



            }
            // 继续扫描下一个
            index++;
            // 判断是否为空
            if (index == express.length()) {
                while (!symbolStack.isEmpty()) {
                    // 符号栈不是空,则从数栈中直接取出两个数直接运算
                    oper = symbolStack.pop();
                    num1 = numberStack.pop();
                    num2 = numberStack.pop();
                    // 进行运算
                    int result = symbolStack.operation(oper, num1, num2);
                    // 将结果入数栈
                    numberStack.push(result);
                }
                break;
            }
        }
        System.out.printf("计算结果为:%d", numberStack.pop());
        System.out.println();
    }
}

@Data
class ArrayStack2 {
    // 栈的大小
    private final int size;
    // 存放栈元素的数组
    private final int[] arrayStack;
    // 栈顶指针
    private int top;

    /**
     * 使用构造方法初始化栈
     * @param size 栈的大小
     */
    public ArrayStack2 (int size) {
        this.size = size;
        this.arrayStack = new int[size];
        this.top = -1;
    }

    /**
     * 判断栈是否已满
     * @return true(满了)  false (没满)
     */
    public boolean isFull () {
        return top == size - 1;
    }

    /**
     * 判断栈是否为空
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 入栈
     */
    public void push (int data) {
        if (isFull()) {
            throw new RuntimeException("栈已经满了,无法入栈");
        }
        arrayStack[++top] = data;
    }

    /**
     * 出栈
     * @return 弹出返回栈顶
     */
    public int pop () {
        if (isEmpty()) {
            throw new RuntimeException("栈为空,无法进行出栈操作");
        }

        return arrayStack[top--];
    }

    /**
     * 遍历栈
     */
    public void show() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空,无法进行遍历");
        }

        for (int i = top; i >= 0; i--) {
            System.out.printf("arrayStack[%d] = %d\n" ,i ,arrayStack[i]);
        }
    }

    /**
     * 返回当前栈顶的元素
     */
    public int peek() {
        return arrayStack[top];
    }

    /**
     * 判断优先级
     */
    public int getPriority(int c1) {
        if (c1 == '*' || c1 == '/') {
            return 1;
        }
        return 0;
    }

    /**
     * 判断是否是符号
     */
    public  boolean isOper(char ch) {
        if ('*' == ch || '+' == ch || '-' == ch || '/' == ch) {
            return true;
        }
        return false;
    }

    /**
     * 根据符号将两个数进行运算
     */
    public int operation (int symbol, int num1, int num2) {
        // 用于存放结果
        int result = 0;
        switch (symbol) {
            case '*':
                result =  num1 * num2;
                break;
            case '/':
                result =  num1 / num2;
                break;
            case '-':
                result =  num2 - num1;
                break;
            case '+':
                result =  num1 + num2;
                break;
            default:
                break;
        }
        return result;
    }
}