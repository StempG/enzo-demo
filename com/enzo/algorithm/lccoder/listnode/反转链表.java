package com.enzo.algorithm.lccoder.listnode;


/**
 * https://www.nowcoder.com/activity/oj
 */
public class 反转链表 {


    private static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }


    public static ListNode ReverseList(ListNode head) {
        if(head == null){
            return null;
        }

        ListNode pre = null;//保存前置指针
        ListNode cur = head;//记录当前指针
        while(cur != null){
            ListNode temp = cur.next;//保存下一个指针

            cur.next = pre;
            pre = cur;
            cur = temp;

        }
        return pre;

    }


    private static ListNode tc(){
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(1);
        n1.next = n2;
        n2.next = n3;

        return n1;


    }



    public static void main(String[] args) {

        System.out.println(ReverseList(tc()));

    }
}
