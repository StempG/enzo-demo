package com.enzo.algorithm.lccoder.tree;

import java.util.ArrayList;
import java.util.List;

//https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
//二叉树的层序遍历


public class 二叉树的层序遍历 {


    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }




    public static List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();

        //递归遍历
        if (root != null){
            put(root, 0, result);
        }

        return result;
    }

    private static void put(TreeNode node, int level,List<List<Integer>> result ){
        if (level >= result.size()){
            //初始化该层的list
            result.add(new ArrayList<>());
        }


        //开始放入node
        result.get(level).add(node.val);
        //遍历下一层，从左到右
        if (node.left != null){
            put(node.left, level+1, result);
        }
        if (node.right != null){
            put(node.right, level+1, result);
        }

    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);

        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println(levelOrder(root));
    }


}
