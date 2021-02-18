package com.enzo.algorithm.lccoder.listnode;


/**
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 *
 * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
 *
 * 示例 1:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 1->3->5->2->4->NULL
 * 示例 2:
 *
 * 输入: 2->1->3->5->6->4->7->NULL
 * 输出: 2->3->6->7->1->5->4->NULL
 * 说明:
 *
 * 应当保持奇数节点和偶数节点的相对顺序。
 * 链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/odd-even-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 奇偶链表 {


    private static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }


    public static ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }

        ListNode jDummy = new ListNode(-1);
        ListNode jTail = jDummy;
        ListNode oDummy = new ListNode(-1);
        ListNode oTail = oDummy;

        jDummy.next = jTail;
        oDummy.next = oTail;

        ListNode cur = head;
        boolean isJ = true;
        while (cur != null){
            if (isJ){
                jTail.next = cur;
                jTail = cur;
                cur = cur.next;
                jTail.next = null;
                isJ = false;
            }else {
                oTail.next = cur;
                oTail = cur;
                cur = cur.next;
                oTail.next = null;
                isJ = true;
            }
        }

        jTail.next = oDummy.next;

        return jDummy.next;







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

        System.out.println(oddEvenList(tc()));

    }
}
