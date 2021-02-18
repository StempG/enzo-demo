package com.enzo.algorithm.lccoder.listnode;


/**
 *
 * https://leetcode-cn.com/problems/rotate-list/
 *
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 *
 * 示例1:
 *
 * 输入: 1->2->3->4->5->NULL, k = 2
 * 输出: 4->5->1->2->3->NULL
 * 解释:
 * 向右旋转 1 步: 5->1->2->3->4->NULL
 * 向右旋转 2 步: 4->5->1->2->3->NULL
 * 示例2:
 *
 * 输入: 0->1->2->NULL, k = 4
 * 输出: 2->0->1->NULL
 * 解释:
 * 向右旋转 1 步: 2->0->1->NULL
 * 向右旋转 2 步: 1->2->0->NULL
 * 向右旋转 3 步:0->1->2->NULL
 * 向右旋转 4 步:2->0->1->NULL
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rotate-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class 旋转链表 {


    private static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }



    public static ListNode rotateRight(ListNode head, int k) {

        //思路
        //可以转化为求倒数第K个节点。然后把K节点之后到尾结点的部分作为头结点，并且连接起来

        if (head == null || k<=0){
            return head;
        }

        //首先获取链表的长度，因为K是可以比链表长度还大
        int size = 0;
        ListNode cur = head;
        while (cur != null){
            size++;
            cur = cur.next;
        }


        int j = k%size;//获取倒数第J个节点
        if (j == 0 ){
            return head;
        }

        ListNode slow = head;
        ListNode fast = head;
        for(int i=0;i<j;i++){
            fast =fast.next;
        }


        ListNode fastPre = null;
        ListNode slowPre = null;

        while (fast!=null){
            slowPre = slow;
            fastPre = fast;
            fast = fast.next;
            slow = slow.next;
        }




        if (slowPre==null){
            return slow;
        }


        slowPre.next = null;
        fastPre.next = head;


        return slow;



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
        ListNode result = rotateRight(tc(), 2);
        System.out.println(result);

    }
}
