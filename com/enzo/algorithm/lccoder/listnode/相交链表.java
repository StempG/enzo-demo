package com.enzo.algorithm.lccoder.listnode;


/**
 *
 编写一个程序，找到两个单链表相交的起始节点。

 https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
 */
public class 相交链表 {


    private static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }



    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null){
            return null;
        }

        ListNode pA = headA;
        ListNode pB = headB;

        while (pA != pB){
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }

        return pA;


    }
    //反转链表
    public static ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
    private static ListNode tc(){
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        return n1;


    }



    public static void main(String[] args) {
//        boolean result = getIntersectionNode(tc(), );
//        System.out.println(result);

    }
}
