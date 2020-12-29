package com.atguigu.queue;

/**
 * @Author 汪玉龙
 * @title 数组实现循环队列
 * @Date 2020/12/28 21:30
 */
public class CircleArrayQueueDemo {

    public static void main(String[] args) {
          CircleArrayQueue queue = new CircleArrayQueue(4);
                   queue.push(1);
                   queue.push(2);
                   queue.push(3);
                   queue.showQueue();

          CircleArrayQueue queue1 = new CircleArrayQueue(4);
                  queue1.push(1);
                  queue1.push(2);
                  queue1.push(3);
                  System.out.println(queue1.pop());
                  queue1.push(4);
                  System.out.println(queue1.pop());
                  System.out.println(queue1.pop());
                 System.out.println(queue1.pop());
                  queue.showQueue();
    }
}

class CircleArrayQueue {
    /*
     * 默认指向队列的头部元素
     */
    private int front;

    /**
     * 默认指向队列最后一个元素的后一个位置
     */
    private int real;

    /**
     * 队列的最大大小
     */
    private final int maxSize;

    /**
     * 存放队列元素的数组
     */
    private final int[] queue;

    public CircleArrayQueue(int size) {
        queue = new int[size];
        maxSize = size;
    }

    /**
     * 入队操作
     * @param data 入队列数据
     */
    public void push(int data) {
        // 判断队列是否已经满了
        if (isFullQueue()) {
            throw new RuntimeException("队列满了, 无法执行入队操作");
        }
        // 进行入队操作
        queue[real] = data;
        // 调整real位置
        real = (real + 1) % maxSize;
    }

    /**
     * 队列是否已经满了
     * @return true 满了 false没有满
     */
    private boolean isFullQueue() {
        return (real + 1) % maxSize == front;
    }

    /**
     * 出队操作
     */
    public int pop() {
        // 队列是否为空
        if (isEmptyQueue()) {
            throw new RuntimeException("队列为空, 无法进行出队操作");
        }
        // 进行出队操作
        int result = queue[front];
        // 调整front位置
        front = (front + 1) % maxSize;
        return result;
    }

    /**
     * 队列是否为空
     * @return true 空 false 非空
     */
    private boolean isEmptyQueue() {
        return front == real;
    }

    /**
     * 返回队列的头部元素
     * @return 队头元素
     */
    public int top() {
        // 队列是否为空
        if (isEmptyQueue()) {
            throw new RuntimeException("队列为空, 无法进行出队操作");
        }
        return queue[front];
    }

    /**
     * 显示队列元素
     */
    public void showQueue() {
        while (!isEmptyQueue()) {
            int data = queue[front];
            front = (front + 1) % maxSize;
            System.out.printf("%d\t", data);
        }
    }
}