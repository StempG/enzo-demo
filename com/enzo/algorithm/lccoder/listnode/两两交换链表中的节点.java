package com.enzo.algorithm.lccoder.listnode;

//https://leetcode-cn.com/problems/swap-nodes-in-pairs/
public class 两两交换链表中的节点 {

    private static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }


    }

    public static ListNode swapPairs(ListNode head) {
        //1.跳出递归的条件判断
        if (head == null || head.next == null) {
            return head;
        }

        //2.逻辑实现
        ListNode newHead = head.next;

        //递归
        head.next = swapPairs(newHead.next);//递归实现
        newHead.next = head;//交换第一组

        return newHead;
    }


    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);

        l1.next = l2;
        l2.next = l3;
        l3.next = l4;

        System.out.println(swapPairs(l1));

    }

}
