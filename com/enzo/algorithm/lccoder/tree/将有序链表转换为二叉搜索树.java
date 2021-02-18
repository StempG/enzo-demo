package com.enzo.algorithm.lccoder.tree;

/**
 * https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/
 * <p>
 * 108. 将有序数组转换为二叉搜索树
 * 难度
 * 简单
 * <p>
 * 676
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 * <p>
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 * <p>
 * 示例:
 * <p>
 * 给定有序数组: [-10,-3,0,5,9],
 * <p>
 * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 * <p>
 * 0
 * / \
 * -3   9
 * /   /
 * -10  5
 */
public class 将有序链表转换为二叉搜索树 {


    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


    public static void main(String[] args) {

        ListNode head = new ListNode(-10);
        head.next = new ListNode(-3);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(5);
        head.next.next.next.next = new ListNode(9);

        TreeNode result = sortedListToBST(head);
        System.out.println(result);

    }


    /**
     * 中序遍历是有序的
     * 选择中间的节点作为根，则左右子树的高度差则会小于1
     */

    public static TreeNode sortedListToBST(ListNode head) {
        if (head == null){
            return null;
        }

        ListNode tail = head;
        while (tail.next != null){
            tail = tail.next;
        }

        return helpBuild(head, tail);

    }

    private static TreeNode helpBuild(ListNode head, ListNode tail) {

        if (head == null || tail ==null){
            return null;
        }
        if (head == tail){
            return new TreeNode(head.val);
        }


        //找到中间节点
        ListNode fast = head;
        ListNode slow = head;
        ListNode pre = null;
        while(fast != tail && fast.next != tail){
            fast = fast.next.next;

            pre = slow;
            slow = slow.next;
        }

        TreeNode root = new TreeNode(slow.val);

        root.left = helpBuild(head, pre);
        root.right = helpBuild(slow.next, tail);



        return root;
    }


}
