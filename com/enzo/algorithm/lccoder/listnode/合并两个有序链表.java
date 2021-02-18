package com.enzo.algorithm.lccoder.listnode;


/**
 * 【21】  合并两个有序链表
 * https://leetcode-cn.com/problems/merge-two-sorted-lists/
 */
// 示例：
//
// 输入：1->2->4, 1->3->4
//输出：1->1->2->3->4->4
//
// Related Topics 链表



public class 合并两个有序链表 {


    static class ListNode{
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        ListNode l1 =new ListNode(1);
        l1.next = new ListNode(3);
        l1.next.next = new ListNode(5);
        l1.next.next.next = new ListNode(7);


        ListNode l2 =new ListNode(2);
        l2.next  = new ListNode(10);

        ListNode listNode = mergeTwoLists(l1, l2);

        while (listNode != null){
            System.out.println(listNode.val);
            listNode = listNode.next;
        }

    }
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        //异常处理
        if (l1 == null){
            return l2;
        }
        if (l2 == null){
            return l1;
        }


        ListNode head = new ListNode(0);

        ListNode current = head;

        while (true){
            //如果两个链表其中有一个已经遍历完，则将剩余的那个链表添加到结果的最后
            if (l1 == null){
                current.next = l2;
                break;
            }
            if (l2 == null){
                current.next= l1;
                break;
            }


            //大小判断
            if (l1.val >= l2.val){
                current.next = l2;
                l2 = l2.next;
                current = current.next;
            }else {
                current.next = l1;
                l1 = l1.next;
                current = current.next;
            }
        }

        return head.next;

    }

}
