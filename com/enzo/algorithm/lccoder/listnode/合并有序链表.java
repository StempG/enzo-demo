package com.enzo.algorithm.lccoder.listnode;


/**
 * https://www.nowcoder.com/practice/a479a3f0c4554867b35356e0d57cf03d?tpId=117&&tqId=34954&&companyId=665&rp=1&ru=/company/home/code/665&qru=/ta/job-code-high/question-ranking
 *
 *
 * 以下的题是相同的
 * @see com.enzo.algorithm.lccoder.listnode.合并两个有序链表
 */
public class 合并有序链表 {


    private static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }


    public static ListNode mergeTwoLists (ListNode l1, ListNode l2) {
        // write code here
        //异常处理
        if (l1 == null){
            return l2;
        }
        if (l2 == null){
            return l1;
        }


        ListNode head = new ListNode(0);

        ListNode current = head;

        while (true){
            //如果两个链表其中有一个已经遍历完，则将剩余的那个链表添加到结果的最后
            if (l1 == null){
                current.next = l2;
                break;
            }
            if (l2 == null){
                current.next= l1;
                break;
            }


            //大小判断
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
}
