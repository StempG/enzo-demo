package com.enzo.algorithm.lccoder.tree;


import java.util.ArrayList;
import java.util.List;

//
//https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
public class 二叉树的后序遍历 {

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

        //递归解法
        System.out.println(postorderTraversal(t1));



    }

//    public static List<Integer> postorderTraversal_loop(TreeNode root) {
//        if (root == null){
//            return new ArrayList<>();
//        }
//
//        Stack<TreeNode>  stack = new Stack<>();
//        stack.push(root);
//
//        Stack<Integer> collection = new Stack<>();
//        while (!stack.isEmpty()){
//            TreeNode node = stack.pop();
//            //加入到头部
//            collection.push(node.val);
//
//            if (node.right != null){
//                stack.add(node.right);
//            }
//            if (node.left != null){
//                stack.add(node.left);
//            }
//        }
//
//        if (collection.isEmpty()){
//            return new ArrayList<>();
//        }
//
//        List<Integer> result = new ArrayList<>(collection.size());
//
//        while (!collection.isEmpty()){
//            result.add(collection.pop());
//        }
//        return result;
//    }


    public static List<Integer> postorderTraversal(TreeNode root) {

        List<Integer> list = new ArrayList<>();

        sub(root,list);
        return list;
    }


    public static void sub(TreeNode node, List<Integer> list){
        if (node == null){
            return;
        }

        if (node.left == null && node.right == null){
            list.add(node.val);
            return;
        }


        if (node.left!=null){
            sub(node.left, list);
        }

        if (node.right!=null){
            sub(node.right, list);
        }

        list.add(node.val);
    }

}

