package com.enzo.algorithm.lccoder.tree;

import java.util.Stack;

public class 二叉搜索树的最小绝对差 {


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int getMinimumDifference(TreeNode root) {

        //中序遍历得到一个升序数组
        //求这个数组相邻两个元素的最小值



        int result = Integer.MAX_VALUE;

        //需要依赖一个栈 这样的额外空间
        Stack<TreeNode> stack = new Stack<>();
        //沿着左子树一直下探，栈顶是最深的左孩子节点，直到没有左子树了，开始回溯，到根，再压入右孩子
        TreeNode temp = root;



        TreeNode prev = null;
        while (temp  !=null || !stack.isEmpty()){
            //直到没有左子树了，跳出循环
            while (temp != null){
                stack.push(temp);
                temp = temp.left;
            }


            //开始出栈
            TreeNode node = stack.pop();

            if (prev != null){
                result = Math.min(result, node.val - prev.val);
            }

            prev = node;

//            list.add(node.val);
            temp = node.right;
        }


        return result;






    }

}
