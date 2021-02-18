package com.enzo.algorithm.lccoder.listnode;


/**
 * https://leetcode-cn.com/problems/partition-list/
 * 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
 *
 * 你应当保留两个分区中每个节点的初始相对位置。
 *
 * 
 *
 * 示例:
 *
 * 输入: head = 1->4->3->2->5->2, x = 3
 * 输出: 1->2->2->4->3->5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/partition-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 分隔链表 {


    private static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }


    public static ListNode partition(ListNode head, int x ) {
        if(head == null){
            return null;
        }

        /*
        思路
        设置两个哑节点分别表示比x大的部分跟比x小的部分
        使用尾插法，能保持原来链表中节点的相对位置
        最后连接两个链表
         */


        ListNode current = head;
        ListNode smallDummy = new ListNode(-1);
        ListNode smallTail = smallDummy;
        ListNode bigDummy = new ListNode(-1);
        ListNode bigTail = bigDummy;

        while (current!= null){
            if (current.val<x){
                smallTail.next = current;
                smallTail = current;
                current = current.next;
                smallTail.next = null;//防止tail后面有其他节点，污染链表

            }else {
               bigTail.next = current;
               bigTail = current;
                current = current.next;
                bigTail.next = null;//防止tail后面有其他节点，污染链表
            }
        }

        //链接两段链表
        smallTail.next = bigDummy.next;

        return smallDummy.next;
    }


    private static ListNode tc(){
        ListNode n1 = new ListNode(3);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;

        return n1;


    }



    public static void main(String[] args) {

        System.out.println(partition(tc(),2));

    }
}
