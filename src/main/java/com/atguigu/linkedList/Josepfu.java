package com.atguigu.linkedList;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 汪玉龙
 * @title 约瑟夫环问题-链表解决思路
 * @Date 2021/3/8 22:08
 */
public class Josepfu {

    public static void main(String[] args) {
        // 创建环形链表
        SingleCircleLinkedList linkedList = new SingleCircleLinkedList();
        linkedList.createCircleLinkedList(5);
        linkedList.showCircleLinkedList();
        linkedList.countBoy(1, 2, 5);
    }

    @Data
    static class Boy {
        // 编号
        private int no;
        // 指向下一个节点的指针
        private Boy next;

        public Boy(int no) {
            this.no = no;
        }
    }

    @Data
    @NoArgsConstructor
    static class SingleCircleLinkedList {
        // 创建一个空节点
        Boy first = new Boy(-1);

        /**
         * 创建环形链表
         */
        public void createCircleLinkedList(int nums) {
            if (nums < 1) {
                throw new IllegalArgumentException("传入的数据有误");
            }
            Boy currentNode = null;
            for (int i = 1; i <= nums; i++) {
                if (i == 1) {
                    first = new Boy(i);
                    currentNode = first;
                    continue;
                }
                Boy newBoy = new Boy(i);
                currentNode.next = newBoy;
                newBoy.next = first;
                currentNode = newBoy;
            }
        }


        /**
         * 遍历单向环形链表
         */
        public void showCircleLinkedList() {
            if (first == null) {
                System.out.println("遍历的链表为空");
                return;
            }
            // 做一个辅助指针
            Boy currentBoy = first;
            while (true) {
                System.out.printf("小孩子的编号为%d \n", currentBoy.no);
                if (currentBoy.next == first) {
                    break;
                }
                currentBoy = currentBoy.next;
            }
        }

        /**
         * 明天完成
         *
         * @param startNo 从第几个孩子开始报数
         * @param count   每次数几个出圈
         * @param nums    一共几个孩子
         */
        public void countBoy(int startNo, int count, int nums) {
            if (startNo > nums) {
                throw new IllegalArgumentException("输入参数有误!");
            }
            // 定义帮助指针
            Boy helper = first;
            // 首先让帮助指针来到链表的尾部
            while (true) {
                if (helper.next == first) {
                    break;
                }
                helper = helper.next;
            }
            for (int i = 0; i < startNo - 1; i++) {
                first = first.next;
                helper = helper.next;
            }
            // 然后开始出圈游戏
            while (true) {
                if (first == helper) {
                    break;
                }
                for (int i = 0; i < count - 1; i++) {
                    first = first.next;
                    helper = helper.next;
                }
                System.out.println(first.no);
                helper.next = first.next;
                first = first.next;
            }
            System.out.println(first.no);
        }
    }

}
