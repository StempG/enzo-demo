package com.enzo.algorithm.lccoder.tree;

/**
 * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * <p>
 * 105. 从前序与中序遍历序列构造二叉树
 * 难度
 * 中等
 * <p>
 * 826
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 * <p>
 * 注意:
 * 你可以假设树中没有重复的元素。
 * <p>
 * 例如，给出
 * <p>
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 */
public class 从前序与中序遍历序列构造二叉树 {


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


    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
       return helpBuild(preorder, inorder, 0, preorder.length, 0, inorder.length);
    }

    /**
     *
     * @param preorder 前序遍历数组
     * @param inorder 中序遍历数组
     * @param pLeft 本次方法中，前序数组的左边界下标
     * @param pRight 本次方法中，前序数组的右边界下标（开区间）
     * @param iLeft 本次方法中，中序数组的左边界下标
     * @param iRight 本次方法中，中序数组的右边界下标（开区间）
     */
    private static TreeNode helpBuild(int[] preorder, int[] inorder, int pLeft, int pRight, int iLeft, int iRight) {
        if (pLeft >= pRight){//前序遍历的子数组已经为空了
            return null;
        }

        int rootVal = preorder[pLeft];

        TreeNode root = new TreeNode(rootVal);

        //通过rootVal，分割中序遍历数组，[iLeft,rootIndexOfIn)是左子树，[rootIndexOfIn+1, iRight)是右子树
        int rootIndexOfIn = 0;
        for (int i = iLeft;i<iRight;i++){
            if (inorder[i] == rootVal){
                rootIndexOfIn = i;
                break;
            }
        }

        //左子树的长度，下面用来确定前序遍历数组中，左子树的元素个数
        int leftTreeLength = rootIndexOfIn - iLeft;

        root.left = helpBuild(preorder, inorder, pLeft+1, pLeft +1 +leftTreeLength, iLeft, rootIndexOfIn);
        root.right = helpBuild(preorder, inorder, pLeft +1 +leftTreeLength, pRight, rootIndexOfIn+1, iRight);

        return root;
    }


}
