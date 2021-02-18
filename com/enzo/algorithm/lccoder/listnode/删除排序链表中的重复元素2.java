package com.enzo.algorithm.lccoder.listnode;


/**
 *
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/
 *
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中没有重复出现的数字。
 *
 * 示例1:
 *
 * 输入: 1->2->3->3->4->4->5
 * 输出: 1->2->5
 * 示例2:
 *
 * 输入: 1->1->1->2->3
 * 输出: 2->3
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class 删除排序链表中的重复元素2 {


    private static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }




    public static ListNode deleteDuplicates(ListNode head) {

        /*
        参考这人的解法
        使用左右双指针，区间长度判断来确定是否

        https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/solution/javashuang-zhi-zhen-dai-ma-jiao-duan-rong-yi-li-ji/
         */

        if (head == null) {
            return null;  // 若head为空则直接返回null
        }
        ListNode dummy = new ListNode(-1);  // 建立一个虚拟头结点
        ListNode tail = dummy;  // 定义一个尾巴，用于尾插法。

        //初始化区间
        //左指针如果到末尾，则表示链表遍历结束
        //每次区间判断完毕后，进行新的区间赋值
        for (ListNode l = head, r = head; l != null; l = r) {

            while (r != null && r.val == l.val) {
                r = r.next;  // 只要r不为空并且与l的值相等则一直向后移动，因为是个排序链表
            }

            if (l.next == r) {  // 若长度为1，则通过尾插法加入。
                tail.next = l;  // 基本的尾插法
                tail = l;
                tail.next = null;  // 这里记得将尾部的后面置为null，不然可能后面会带着一些其他的节点。
            }
        }
        return dummy.next;


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
