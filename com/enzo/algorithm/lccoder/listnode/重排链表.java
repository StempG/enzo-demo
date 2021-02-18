package com.enzo.algorithm.lccoder.listnode;


/**
 * https://www.nowcoder.com/practice/3d281dc0b3704347846a110bf561ef6b?tpId=190&&tqId=35177&rp=1&ru=/ta/job-code-high-rd&qru=/ta/job-code-high-rd/question-ranking
 */
public class 重排链表 {



    private static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }


    /**
     * 以下是未看过答案的自己的思路
     *
     * 1.遍历链表，将整个链表分为两个链表，通过替换next指针来完成链表的分割
     * 2.遍历两个链表，将两个链表重新合并为新的链表。
     *
     *
     * 以下是看过答案后的思路
     *
     * 1.遍历链表找到中点，将链表分隔为L1,L2两段
     * 2.将L2反转
     * 3.将反转后的L2与L1交替合并
     *
     * @param head
     */
    public static void reorderList(ListNode head) {

        if (head == null || head.next == null){
            return;
        }

        ListNode mid = findMid(head);
        ListNode l1 = head;
        ListNode l2 = mid.next;
        mid.next = null;//断开链表，也是为了下面反转做准备，以免指针污染
        ListNode rL2 = reverse(l2);

        merge(l1, rL2);

    }

    private static void merge(ListNode l1, ListNode l2) {
        ListNode temp1 ;
        ListNode temp2 ;

        while (l1 != null && l2!= null){
            temp1 = l1.next;
            temp2 = l2.next;

            l1.next = l2;
            l1 = temp1;

            l2.next = l1;
            l2 = temp2;

        }

    }

    private static ListNode reverse(ListNode l2) {
        ListNode pre = null;
        ListNode cur = l2;
        while (cur != null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;

    }

    private static ListNode findMid(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }


        return slow;
    }


    public static void main(String[] args) {

        reorderList(tc());
        System.out.println(tc());
        
    }


    private static 重排链表.ListNode tc(){
        重排链表.ListNode n1 = new 重排链表.ListNode(1);
        重排链表.ListNode n2 = new 重排链表.ListNode(2);
        重排链表.ListNode n3 = new 重排链表.ListNode(3);
        重排链表.ListNode n4 = new 重排链表.ListNode(4);

//        重排链表.ListNode n5 = new 重排链表.ListNode(5);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
//        n4.next = n5;

        return n1;


    }




}
