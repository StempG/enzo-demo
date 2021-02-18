package com.enzo.algorithm.lccoder.listnode;


/**
 * 【23】 合并K个排序链表
 * https://leetcode-cn.com/problems/merge-k-sorted-lists/
 */
//合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
//
// 示例:
//
// 输入:
//[
//  1->4->5,
//  1->3->4,
//  2->6
//]
//输出: 1->1->2->3->4->4->5->6
// Related Topics 堆 链表 分治算法



public class 合并K个排序链表 {


    static class ListNode{
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static void main(String[] args) {


        ListNode[] lists = new ListNode[3];


        mergeKLists(lists);
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        return merge(lists, 0, lists.length-1);

    }



    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        if (l1 == null){
            return l2;
        }
        if (l2 == null){
            return l1;
        }


        ListNode head = new ListNode(0);

        ListNode current = head;

        while (true){
            if (l1 == null){
                current.next = l2;
                break;
            }
            if (l2 == null){
                current.next= l1;
                break;
            }


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





    public static ListNode merge(ListNode[] lists, int left, int right) {

        if (left == right){
            return lists[left];
        }

        if (left>right){
            return null;
        }



        //分治，两两合并
        //

        int mid = (left+right)/2;

       return mergeTwoLists(merge(lists, left, mid),merge(lists, mid+1, right) );


    }




}
