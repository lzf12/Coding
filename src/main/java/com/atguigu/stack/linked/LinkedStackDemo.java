package com.atguigu.stack.linked;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 汪玉龙
 * @title  使用链表实现一个栈 (逆序构建单链表)
 * @Date 2021/3/14 19:14
 */
public class LinkedStackDemo {

    public static void main(String[] args) {
        LinkedStack stack = new LinkedStack(5);
        stack.push(0);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.show();
        System.out.println(stack.pop());
        stack.show();
    }
}



class LinkedStack {
    // 初始化一个头节点
    private Node head = new Node(-1);
    // 栈的大小
    private Integer size;
    // 栈顶指针
    private Node top;

    public LinkedStack (Integer size) {
        this.size = size;
        this.top = head;
    }

    /**
     * 判断栈是否为空
     */
    public boolean isEmpty () {
        return top == head;
    }

    /**
     * 判断栈是否已经满了
     */
    public boolean isFull () {
        // 获取栈大小的方法
        Integer currentStackSize = size();
        return currentStackSize.equals(size);
    }

    /**
     * 求栈的大小
     * @return 栈的大小
     */
    public Integer size () {
        if (isEmpty()) {
            return 0;
        }
        // 定义一个变量, 记录当前栈大小
        Integer size = 0;
        // 定义一个辅助指针
        Node help = head;
        while (help.getNext() != null) {
            size++;
            help = help.getNext();
        }
        return size;
    }

    /**
     * 入栈操作
     */
    public void push (Integer no) {
        if (isFull()) {
            throw new RuntimeException("栈满了");
        }

        // 创建一个新的节点
        Node newNode = new Node(no);
        newNode.setNext(top);
        top = newNode;
    }

    /**
     * 出栈操作
     */
    public Integer pop () {
        if (isEmpty()) {
            throw new RuntimeException("栈为空,不能进行出栈");
        }

        Integer data = top.getNo();
        top = top.getNext();
        return data;
    }

    /**
     * 遍历单链表
     */
    public void show () {
        if (isEmpty()) {
            throw new RuntimeException("栈为空,无法遍历");
        }
        // 定义一个辅助指针
        Node help = top;
        while (help != head) {
            System.out.printf("节点编号为:%d\n", help.getNo());
            help = help.getNext();
        }
    }
}


@Data
@NoArgsConstructor
class Node {
    // 节点编号
    private Integer no;
    // 指向下一个节点的指针
    private Node next;

    public Node (Integer no) {
        this.no = no;
    }
}


