package com.enzo.algorithm.lccoder.listnode;


/**
 * https://leetcode-cn.com/problems/insertion-sort-list/
 * 147. 对链表进行插入排序
 对链表进行插入排序。


 插入排序的动画演示如上。从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。
 每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。

  

 插入排序算法：

 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
 重复直到所有输入数据插入完为止。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/insertion-sort-list
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 对链表进行插入排序 {


    private static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }


    public static ListNode insertionSortList(ListNode head) {
        if (head == null ||  head.next == null){
            return head;
        }


        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre ;
        ListNode cur = head;


        while (cur != null && cur.next != null){

            if (cur.val <= cur.next.val){
                cur = cur.next;
            }else {
                ListNode insertNode = cur.next;
                cur.next = cur.next.next;//断开与 需要位移的node的 指针


                pre = dummy;
                while (pre.next.val <= insertNode.val){
                    pre = pre.next;
                }

                ListNode next = pre.next;
                pre.next = insertNode;
                insertNode.next = next;

            }

        }




        return dummy.next;





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
        ListNode listNode = (insertionSortList(tc()));
        System.out.println(listNode);

    }
}
