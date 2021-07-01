package com.enzo.algorithm.lccoder.listnode;


import java.util.HashSet;
import java.util.Set;

/**
 *
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 *
 * 示例1:
 *
 * 输入: 1->1->2
 * 输出: 1->2
 * 示例2:
 *
 * 输入: 1->1->2->3->3
 * 输出: 1->2->3
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class 删除排序链表中的重复元素 {


    private static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }


        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }




    public static ListNode deleteDuplicates(ListNode head) {


        // TODO: 2021/5/11 未ac 以下的解法有问题 
        if (head == null){
            return null;
        }
        Set<Integer>  numbers = new HashSet<>();

        ListNode pre = new ListNode(-1);
        pre.next = head;

        ListNode cur = head;
        while (cur!=null){
            ListNode next = cur.next;
            if (numbers.contains(cur.val)){
                pre.next = next;
                cur = next;
            }else {
                numbers.add(cur.val);
                pre = cur;
                cur = next;
            }
        }

        return head;


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
        ListNode result = deleteDuplicates(tc());
        System.out.println(result);

    }
}
