package com.enzo.algorithm.lccoder.tree;

import java.util.ArrayList;
import java.util.List;

//https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/
//二叉树的锯齿形层序遍历


/**
 * 例如：
 * 给定二叉树[3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回锯齿形层序遍历如下：
 *
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 二叉树的锯齿形层序遍历 {


    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
        TreeNode(int x, TreeNode left, TreeNode right) {
            val = x;
            this.left = left;
            this.right = right;

        }

    }

    private static void put(TreeNode node, int level, List<List<Integer>> result ){
        if (level >= result.size()){
            //初始化该层的list
            result.add(new ArrayList<>());
        }

        boolean startFromLeft = level%2 == 0;

        //开始放入node
        if (startFromLeft){
            result.get(level).add(node.val);
        }else {
            result.get(level).add(0, node.val);
        }

        if (node.left != null){
            put(node.left, level+1, result);
        }
        if (node.right != null){
            put(node.right, level+1, result);
        }

    }


    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {


        List<List<Integer>> result = new ArrayList<>();

        //递归遍历
        if (root != null){
            put(root, 0, result);
        }

        return result;


    }



    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);

        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println(zigzagLevelOrder(root));
    }


}
