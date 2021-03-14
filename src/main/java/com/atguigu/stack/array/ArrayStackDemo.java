package com.atguigu.stack.array;

/**
 * @Author 汪玉龙
 * @title  使用数组实现栈
 * @Date 2021/3/14 18:45
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(5);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.show();
        System.out.println(stack.pop());
        stack.show();
    }
}


class ArrayStack {
    // 栈的大小
    private final Integer size;
    // 存放栈元素的数组
    private final Integer[] arrayStack;
    // 栈顶指针
    private Integer top;

    /**
     * 使用构造方法初始化栈
     * @param size 栈的大小
     */
    public ArrayStack (Integer size) {
        this.size = size;
        this.arrayStack = new Integer[size];
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
    public void push (Integer data) {
        if (isFull()) {
            throw new RuntimeException("栈已经满了,无法入栈");
        }
        arrayStack[++top] = data;
    }

    /**
     * 出栈
     * @return 弹出返回栈顶
     */
    public Integer pop () {
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
}
