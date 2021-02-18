package com.enzo.algorithm.lccoder.tree;

/**
 * https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 * <p>
 * 106. 从中序与后序遍历序列构造二叉树
 * 难度
 * 中等
 * <p>
 * 435
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 * <p>
 * 注意:
 * 你可以假设树中没有重复的元素。
 * <p>
 * 例如，给出
 * <p>
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * 返回如下的二叉树：
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 */
public class 从中序与后序遍历序列构造二叉树 {


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
        TreeNode result = buildTree(new int[]{3,2,1}, new int[]{3,2,1});

        System.out.println(result);
    }

    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        return helpBuild(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
    }

    /**
     * @param inOrder   中序遍历数组  （左-根-右）
     * @param postOrder 后序遍历数组 （左-右-根）
     * @param pLeft     本次方法中，后序数组的左边界下标
     * @param pRight    本次方法中，后序数组的右边界下标（闭区间）
     * @param iLeft     本次方法中，中序数组的左边界下标
     * @param iRight    本次方法中，中序数组的右边界下标（闭区间）
     */
    private static TreeNode helpBuild(int[] inOrder, int[] postOrder, int iLeft, int iRight, int pLeft, int pRight) {
        if (iLeft > iRight ||pLeft>pRight) {
            return null;
        }
        if (iLeft == iRight){
            return new TreeNode(inOrder[iLeft]);
        }



        int rootVal = postOrder[pRight];//左右根，根就是后序遍历数组的最后一个节点

        TreeNode root = new TreeNode(rootVal);

        //通过rootVal，分割中序遍历数组，[iLeft,rootIndexOfIn-1]是左子树，[rootIndexOfIn+1, iRight]是右子树
        int rootIndexOfIn = 0;
        for (int i = iLeft; i <= iRight; i++) {
            if (inOrder[i] == rootVal) {
                rootIndexOfIn = i;
                break;
            }
        }

        //右子树的长度，下面用来确定后序遍历数组中，右子树的元素个数
        int leftTreeLength = rootIndexOfIn - iLeft;

        root.left = helpBuild(inOrder, postOrder, iLeft, rootIndexOfIn - 1, pLeft, pLeft + leftTreeLength-1);
        root.right = helpBuild(inOrder, postOrder, rootIndexOfIn + 1, iRight, pLeft+leftTreeLength, pRight - 1);

        return root;
    }


}
