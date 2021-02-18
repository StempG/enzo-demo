package com.enzo.algorithm.lccoder.tree;

/**
 * https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/
 *
 * 108. 将有序数组转换为二叉搜索树
 * 难度
 * 简单
 *
 * 676
 *
 *
 *
 *
 *
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 示例:
 *
 * 给定有序数组: [-10,-3,0,5,9],
 *
 * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 */
public class 将有序数组转换为二叉搜索树 {


      private static class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode() {}
          TreeNode(int val) { this.val = val; }
          TreeNode(int val, TreeNode left, TreeNode right) {
              this.val = val;
              this.left = left;
              this.right = right;
          }
      }


    public static void main(String[] args) {




        TreeNode result=  sortedArrayToBST(new int[]{-10,-3,0,5,9});
        System.out.println(result);

    }


    /**
     * 中序遍历是有序的
     * 选择中间的节点作为根，则左右子树的高度差则会小于1
     */
    public static TreeNode sortedArrayToBST(int[] nums) {
        return helpBuild(nums, 0, nums.length-1);
    }

    private static TreeNode helpBuild(int[] nums, int left, int right) {
        if (left>right){
            return null;
        }

        int rootIndex = (left + right) /2;

        TreeNode root = new TreeNode(nums[rootIndex]);

        root.left = helpBuild(nums, left, rootIndex-1);
        root.right = helpBuild(nums, rootIndex+1, right);

        return root;
    }


}
