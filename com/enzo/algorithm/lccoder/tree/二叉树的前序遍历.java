package com.enzo.algorithm.lccoder.tree;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
 *
 */
public class 二叉树的前序遍历 {

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

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);

        t1.right = t2;
        t2.left =t3;

        //迭代解法
        System.out.println(preorderTraversal(t1));



    }


    public static List<Integer> preorderTraversal(TreeNode root) {
        //需要依赖一个栈 这样的额外空间

        //异常判断
        if (root == null){
            return new ArrayList<>();
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);



        List<Integer> list = new ArrayList<>();
        while (!stack.isEmpty()){

            //父亲节点先出栈。
            TreeNode node = stack.pop();
            list.add(node.val);
            //先压入右孩子，再压入左孩子。因为这样出栈的时候才能保证  是左-->右

            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null){
                stack.push(node.left);
            }
        }

        return list;

    }



}

