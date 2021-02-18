package com.enzo.algorithm.lccoder.listnode;


/**
 * https://www.nowcoder.com/practice/650474f313294468a4ded3ce0f7898b9?tpId=190&&tqId=35179&rp=1&ru=/ta/job-code-high-rd&qru=/ta/job-code-high-rd/question-ranking
 */
public class 判断链表中是否有环 {


    private static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }



    /*
    可以关注下这个题解.
    方法论很有参考意义
    https://leetcode-cn.com/problems/linked-list-cycle/solution/yi-wen-gao-ding-chang-jian-de-lian-biao-wen-ti-h-2/
     */
    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null){
            return  false;
        }

        ListNode fast = head;
        ListNode slow = head;


        while (fast!= null){

            //fast 先走
            fast = fast.next;
            if (fast!=null){
                fast = fast.next;//fast走两步
            }

            if (fast == slow){//如果fast追上了slow，则有环
                return true;
            }

            //否则，slow走一步
            slow = slow.next;

        }

        return false;



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

        System.out.println(hasCycle(tc()));

    }
}
