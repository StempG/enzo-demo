package com.enzo.algorithm.lccoder.listnode;


import java.util.Stack;

/**
 *
 *
 *
 * https://www.nowcoder.com/practice/c56f6c70fb3f4849bc56e33ff2a50b6b?tpId=188&&tqId=36715&rp=1&ru=/activity/oj&qru=/ta/job-code-high-week/question-ranking
 *
 * 假设链表中每一个节点的值都在 0 - 9 之间，那么链表整体就可以代表一个整数。
 * 给定两个这种链表，请生成代表两个整数相加值的结果链表。
 * 例如：链表 1 为 9->3->7，链表 2 为 6->3，最后生成新的结果链表为 1->0->0->0。
 */
public class 两个链表生成相加链表 {


    private static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }


    public static ListNode addInList (ListNode head1, ListNode head2) {

        if (head1 == null){
            return head2;
        }
        if (head2 == null){
            return head1;
        }

        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        ListNode cur1 = head1;
        while (cur1!=null){
            stack1.push(cur1.val);
            cur1 = cur1.next;
        }
        ListNode cur2 = head2;
        while (cur2!=null){
            stack2.push(cur2.val);
            cur2 = cur2.next;
        }


        int maxSize = Math.max(stack1.size(), stack2.size());

        ListNode head = null;
        boolean needIncrease = false;
        for (int i=0;i<maxSize;i++){

            int v1 = 0;
            int v2 = 0;
            if (stack1.size()>0){
                v1 = stack1.pop();
            }
            if (stack2.size()>0){
                v2 = stack2.pop();
            }


            int v = v1+v2;
            if (needIncrease){
                v = v+1;
            }

            needIncrease =( v /10 ) >0;
            ListNode newHead = new ListNode(v%10);
            newHead.next = head;
            head = newHead;
        }

        if (needIncrease){
            ListNode h = new ListNode(1);
            h.next = head;
            return h;
        }

        return head;


    }


    private static ListNode tc(){
        ListNode n1 = new ListNode(9);
        ListNode n2 = new ListNode(3);
        ListNode n3 = new ListNode(7);
        n1.next = n2;
        n2.next = n3;

        return n1;


    }


    private static ListNode tc2(){
        ListNode n1 = new ListNode(6);
        ListNode n2 = new ListNode(3);
        n1.next = n2;

        return n1;


    }


    public static void main(String[] args) {
        ListNode listNode = addInList(tc(), tc2());
        System.out.println(listNode);

    }
}
