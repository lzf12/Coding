package com.atguigu.linkedList;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @Author 汪玉龙
 * @title 实现单链表的添加、删除、修改、查询操作
 * @Date 2021/1/3 21:13
 */
public class LinkedListDemo {

   public static void main(String[] args) {
      SingleLinkedList linkedList = new SingleLinkedList();
      HeronNode node1 = new HeronNode(1, "宋江", "及时雨",null);
      HeronNode node2 = new HeronNode(2, "卢俊义", "玉麒麟",null);
      HeronNode node3 = new HeronNode(3, "吴用", "智多星",null);
      HeronNode node4 = new HeronNode(4, "林冲", "豹子头",null);

      /*linkedList.addNode(node1);
      linkedList.addNode(node2);
      linkedList.addNode(node4);
      linkedList.addNode(node3);
      linkedList.list();*/

      linkedList.addNodeByOrder(node3);
      linkedList.addNodeByOrder(node2);
      linkedList.addNodeByOrder(node4);
      linkedList.addNodeByOrder(node1);
      linkedList.addNodeByOrder(node3);
      linkedList.list();
   }

}


@Data
@Builder
@AllArgsConstructor
class HeronNode {
   public int no;
   public String name;
   public String nickName;
   public HeronNode next;

   @Override
   public String toString() {
      return "HeronNode{" +
              "no=" + no +
              ", name='" + name + '\'' +
              ", nickName='" + nickName + '\'' +
              '}';
   }
}


class SingleLinkedList {
   // 初始化一个头节点, 不带数据
   private final HeronNode head = HeronNode.builder().no(0).build();

   /**
    * 添加节点
    */
   public void addNode(HeronNode node) {
      HeronNode temp = head;
      while (temp.getNext() != null) {
         temp = temp.next;
      }
      // 说明temp已经指向链表的最后一个元素
      temp.next = node;
   }

   /**
    * 按照顺序添加,相当于在内存当中排好序
    */
   public void addNodeByOrder(HeronNode node) {
      HeronNode temp = head;
      boolean flag = false;
      while(temp.next != null) {
         if (temp.next.no > node.no) {
             break;
         }
         if (temp.next.no == node.no) {
            flag = true;
            break;
         }
         temp = temp.next;
      }
      if (flag) {
         System.out.printf("添加的编号%d已经存在,不能添加\n", node.no);
         return;
      }
      node.next = temp.next;
      temp.next = node;
   }

   /**
    * 显示链表
    */
   public void list() {
      HeronNode temp = head.next;
      while(temp != null) {
         System.out.println(temp);
         temp = temp.next;
      }
   }
}