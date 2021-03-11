package com.atguigu.linkedList;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Stack;

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

      linkedList.addNode(node1);
      linkedList.addNode(node2);
      linkedList.addNode(node4);
      linkedList.addNode(node3);
      linkedList.list();
      System.out.println();
      /*HeronNode node = linkedList.reverseLinkedList(linkedList.getHeadNode());
      linkedList.list();*/
      linkedList.reversePrint(linkedList.getHeadNode());
      System.out.println();
      linkedList.list();
      /*linkedList.addNodeByOrder(node3);
      linkedList.addNodeByOrder(node2);
      linkedList.addNodeByOrder(node4);
      linkedList.addNodeByOrder(node1);
      linkedList.list();
      System.out.println();
      HeronNode deleteNode1 = HeronNode.builder().no(1).build();
      HeronNode deleteNode2 = HeronNode.builder().no(2).build();
      HeronNode deleteNode3 = HeronNode.builder().no(3).build();
      HeronNode deleteNode4 = HeronNode.builder().no(4).build();
      HeronNode deleteNode5 = HeronNode.builder().no(5).build();*/
      /*linkedList.deleteNode(deleteNode1);
      linkedList.deleteNode(deleteNode2);
      linkedList.deleteNode(deleteNode3);
      linkedList.list();*/
      /*System.out.println(SingleLinkedList.getLength(linkedList.getHeadNode()));
      HeronNode lastIndexNode = SingleLinkedList.findLastIndexNode(linkedList.getHeadNode(), 1);
      System.out.println(lastIndexNode);*/

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
   private  HeronNode head = HeronNode.builder().no(0).build();

   /**
    * 获取链表头节点
    * @return 链表头节点
    */
   public HeronNode getHeadNode() {
      return head;
   }
   /**
    * 添加节点
    */
   public void addNode(HeronNode node) {
      HeronNode temp = head;
      while (temp.next != null) {
         temp = temp.next;
      }
      // 说明temp已经指向链表的最后一个元素, 直接添加节点到最后
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

   /**
    * 修改节点
    */
   public void updateNode(HeronNode heronNode) {
        HeronNode temp = head;
        boolean flag = false;
        while(true) {
           if (temp.next == null) {
              break;
           }
           if (temp.next.no == heronNode.no) {
              temp.next.name = heronNode.name;
              temp.next.nickName = heronNode.nickName;
              flag =  true;
              break;
           }
           temp = temp.next;
        }

        if (!flag) {
           System.out.printf("修改记录不存在no为%d\n", heronNode.no);
        }
   }

   /**
    * 删除节点
    */
   public void deleteNode(HeronNode heronNode) {
      HeronNode temp = head;
      boolean flag = false;
      while(true) {
         if (temp.next == null) {
            break;
         }
         if (temp.next.no == heronNode.no) {
            flag = true;
            break;
         }
         temp = temp.next;
      }

      if(flag) {
         temp.next = temp.next.next;
      } else {
         System.out.printf("没有找到要删除的编号%d", heronNode.no);
      }
   }

   /**
    * 获取链表的有效节点个数
    * @param head 传入的头节点
    * @return 链表中有效的节点个数
    */
    public static int getLength(HeronNode head) {
        if (head.next == null) {
           // 带头节点的空链表
           return 0;
        }
        int length = 0;
        // 不统计头节点
        HeronNode currentNode = head.next;
        while(currentNode != null) {
           length++;
           currentNode = currentNode.next;
        }
        return length;
    }

   /**
    * 反转链表
    * @return 反转后的链表头节点
    */
   public HeronNode reverseLinkedList() {
       // 先将链表反转
       HeronNode lastNode = null;
       HeronNode newHeadNode = head.next;
       HeronNode temp = null;
       while(newHeadNode != null) {
          temp = newHeadNode;
          newHeadNode = newHeadNode.next;
          temp.next = lastNode;
          lastNode = temp;
       }
       head.next = lastNode;
       return head;
   }

   /**
    * 查找链表倒数第k个节点
    * @param head 链表头节点
    * @param k 位置
    * @return 位置下的节点
    */
   public static HeronNode findLastIndexNode(HeronNode head, int k) {
      if (head.next == null) {
          return null;
      }
      // 有效链表的长度
      int length = getLength(head);
      if (k <= 0 || k > length) {
           return null;
      }
      // 得到需要移动的次数
      int sportCount = length - k;
      HeronNode temp = head.next;
      for (int i = 0; i < sportCount; i++) {
         temp = temp.next;
      }
      return temp;
   }

   /**
    * 将单链表反转 改变链表的结构
    * @param heronNode 链表头节点
    * @return  反转后的链表头节点
    */
   public HeronNode reverseLinkedList(HeronNode heronNode) {
      // 只有一个元素,或者没有元素直接返回本身就可以
      if (heronNode == null || heronNode.next == null) {
         return heronNode;
      }
      // 新建一个新的节点为头节点
      HeronNode newHead = new HeronNode(0,"","",null);
      HeronNode current = heronNode.next;
      HeronNode next = null;
      while (current != null) {
         // 先将下一个节点保存起来
         next = current.next;
         current.next = newHead.next;
         newHead.next = current;
         current = next;
      }
      heronNode.next = newHead.next;
      return heronNode;
   }

   /**
    * 从尾到头打印单链表 注意事项:不能使用反转链表, 反转后链表结构发生变化, 使用栈
    * @param head 链表头节点
    */
   public void reversePrint(HeronNode head) {
      if (head == null || head.next == null) {
         return;
      }
      Stack<HeronNode> stack = new Stack<HeronNode>();
      HeronNode current = head.next;
      while (current != null) {
         stack.push(current);
         current = current.next;
      }

      while (!stack.isEmpty()) {
         System.out.println(stack.pop());
      }
   }
}