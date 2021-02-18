package com.enzo.algorithm.lccoder.listnode;


/**
 * https://www.nowcoder.com/practice/6e630519bf86480296d0f1c868d425ad?tpId=190&&tqId=35178&rp=1&ru=/ta/job-code-high-rd&qru=/ta/job-code-high-rd/question-ranking
 *
 * leetcode 版本
 * https://leetcode-cn.com/problems/linked-list-cycle-lcci/
 */
public class 链表中环的入口 {


    private static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }



    /*
    思路参考这个。
    https://leetcode-cn.com/problems/linked-list-cycle-lcci/solution/liang-chong-jie-jue-fang-shi-zui-hao-de-ji-bai-l-3/
     */
    public static ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            //快慢指针，快指针每次走两步，慢指针每次走一步
            fast = fast.next.next;
            slow = slow.next;
            //先判断是否有环，
            if (slow == fast) {
                //确定有环之后才能找环的入口
                while (head != slow) {
                    //两相遇指针，一个从头结点开始，
                    //一个从相遇点开始每次走一步，直到
                    //再次相遇为止
                    head = head.next;
                    slow = slow.next;
                }
                return slow;
            }
        }
        return null;
    }

//    作者：sdwwld
//    链接：https://leetcode-cn.com/problems/linked-list-cycle-lcci/solution/liang-chong-jie-jue-fang-shi-zui-hao-de-ji-bai-l-3/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。


    private static ListNode tc(){
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(1);
        n1.next = n2;
        n2.next = n3;

        return n1;


    }



    public static void main(String[] args) {

        System.out.println(detectCycle(tc()));

    }
}
