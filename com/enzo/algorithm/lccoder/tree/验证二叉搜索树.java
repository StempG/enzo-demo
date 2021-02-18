package com.enzo.algorithm.lccoder.tree;

/**
 * https://leetcode-cn.com/problems/validate-binary-search-tree/
 * 98. 验证二叉搜索树
 * 难度
 * 中等
 *
 *
 *
 *
 *
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 *
 * 假设一个二叉搜索树具有如下特征：
 *
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 1:
 *
 * 输入:
 *     2
 *    / \
 *   1   3
 * 输出: true
 * 示例 2:
 *
 * 输入:
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 */
public class 验证二叉搜索树 {


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


        TreeNode root = new TreeNode(2);
        TreeNode left = new TreeNode(1);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;


        boolean result=  isValidBST(root);
        System.out.println(result);

    }




    public static boolean isValidBST(TreeNode root) {
         return treeValid(root, null, null);
    }

    private static boolean treeValid(TreeNode tree, Integer minValue, Integer maxValue) {
        if (tree == null){
            return true;
        }

        if (minValue != null && tree.val <= minValue){
            return false;
        }


        if (maxValue != null && tree.val >= maxValue){
            return false;
        }

        if (!treeValid(tree.left, minValue, tree.val)){
            return false;
        }

        if (!treeValid(tree.right, tree.val, maxValue)){
            return false;
        }

        return true;
    }

}
