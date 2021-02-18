package com.enzo.algorithm.lccoder.listnode;

public class 删除链表的倒数第N个节点 {

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


    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
//        n1.next = n2;
//        n2.next = n3;
//        n3.next = n4;
//        n4.next = n5;
        ListNode head = removeNthFromEnd(n1, 1);

        System.out.println(head);

    }


    public static ListNode removeNthFromEnd(ListNode head, int n) {

        int step = 1;
        ListNode slow = head;
        ListNode fast = head;
        ListNode pre = null;
        while (fast != null) {
            if (step <= n) {
                fast = fast.next;
                step++;
                continue;
            }

            pre = slow;
            slow = slow.next;
            fast = fast.next;
        }

        if (pre == null){
            return slow.next;
        }else {
            pre.next = slow.next;

            return head;
        }

    }
}
