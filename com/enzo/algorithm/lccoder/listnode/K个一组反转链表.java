package com.enzo.algorithm.lccoder.listnode;


/**
 *
 * 给你一个链表，每k个节点一组进行翻转，请你返回翻转后的链表。
 *
 * k是一个正整数，它的值小于或等于链表的长度。
 *
 * 如果节点总数不是k的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * 示例：
 *
 * 给你这个链表：1->2->3->4->5
 *
 * 当k= 2 时，应当返回: 2->1->4->3->5
 *
 * 当k= 3 时，应当返回: 3->2->1->4->5
 *
 */
public class K个一组反转链表 {


    private static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }


    /**
     * 使用递归的方法
     * 参考
     * https://leetcode-cn.com/problems/reverse-nodes-in-k-group/solution/di-gui-java-by-reedfan-2/
     * @param head
     * @param k
     * @return
     */
    public static ListNode reverseKGroup(ListNode head, int k) {

        ListNode tail = head;

        for (int i=0;i<k;i++){
            if (tail == null){//如果最后一组的链表长度已经不足k个，则不翻转，直接返回head
                return head;
            }
            tail = tail.next;
        }

        ListNode newHead = reverse(head, tail);

        head.next = reverseKGroup(tail, k);

        return newHead;

    }

    private static ListNode reverse(ListNode head, ListNode tail){

        ListNode pre =null;
        ListNode next ;
        while (head != tail){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
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
        System.out.println(reverseKGroup(tc(), 2));

    }
}
