package com.enzo.algorithm.lccoder.listnode;


/**
 * 给你链表的头结点ListNodeheadListNode，请将其按 升序 排列并返回 排序后的链表 。
 * <p>
 * 进阶：
 * <p>
 * 你可以在ListNodeO(nlog n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 排序链表 {


    private static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }


    public static ListNode sortList(ListNode head) {
        /*
        思路
        因为是nlogn的时间复杂度，第一时间想到的是分治归并思路
        使用递归，将链表不断的分隔成两个部分，直到最小单元的链表只有小于等于2个元素

         */


        // TODO: 2020/12/12 未AC

        //元素小于等于2个
        if (head.next == null) {
            return head;

        }
        if (head.next.next == null) {
            ListNode first = head;
            ListNode second = head.next;
            if (first.val <= second.val) {
                return head;
            } else {
                second.next = first;
                first.next = null;
                return second;
            }
        }


        //
        ListNode[] node = divide(head);
        ListNode listNode = sortList(node[0]);
        ListNode listNode1 = sortList(node[1]);

        return merge(listNode, listNode1);

    }

    private static ListNode merge(ListNode listNode1, ListNode listNode2) {
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        ListNode n1 = listNode1;
        ListNode n2 = listNode2;
        while (n1 != null && n2 != null) {
            if (n1.val > n2.val) {
                tail.next = n2;
                n2 = n2.next;
            } else {
                tail.next = n1;
                n1 = n1.next;
            }

            tail = tail.next;

        }
        if (n1 == null) {
            tail.next = n2.next;
        }
        if (n2 == null) {
            tail.next = n1.next;
        }
        return dummy.next;
    }


    private static ListNode[] divide(ListNode head) {

        ListNode fast = head;
        ListNode slow = head;
        ListNode[] nodes = new ListNode[2];
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;


        }

        nodes[0] = head;
        nodes[1] = slow.next;
        slow.next = null;

        return nodes;

    }


    private static ListNode tc() {
        ListNode n1 = new ListNode(4);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(1);
        ListNode n4 = new ListNode(3);
        ListNode n5 = new ListNode(9);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        return n1;


    }


    public static void main(String[] args) {
        ListNode listNode = sortList(tc());
        System.out.println(listNode);

    }
}
