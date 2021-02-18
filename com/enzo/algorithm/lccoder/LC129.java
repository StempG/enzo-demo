package com.enzo.algorithm.lccoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//https://leetcode-cn.com/problems/sum-root-to-leaf-numbers/
public class LC129 {


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static int sumNumbers(TreeNode root) {
        //回溯算法

        List<Integer> solutions = new ArrayList<>();
        Stack<Integer> route = new Stack<>();
        sou(root, route, solutions);

        return solutions.stream().mapToInt(Integer::intValue).sum();

    }

    private static int calculate(Stack<Integer> stack){
        double result = 0;
        for (int i=0;i<stack.size();i++){
            Integer num = stack.get(i);
            result = result + num * Math.pow(10, stack.size()-i-1);

        }
        return (int)result;
    }

    private static void sou(TreeNode node, Stack<Integer> route, List<Integer> solutions) {
        if (node == null){
            return;
        }

        route.push(node.val);

        if (node.left == null && node.right == null){
            solutions.add(calculate(route));
            route.pop();//回溯
            return;
        }

        if (node.left != null){
            sou(node.left, route, solutions);

        }

        if (node.right != null){
            sou(node.right, route, solutions);
        }


        route.pop();
    }



    private static TreeNode case1(){
        TreeNode root = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        root.left = t2;
        root.right = t3;
        return root;
    }


    private static TreeNode case2(){
        TreeNode root = new TreeNode(4);
        TreeNode t2 = new TreeNode(9);
        TreeNode t3 = new TreeNode(0);
        TreeNode t4 = new TreeNode(5);
        TreeNode t5 = new TreeNode(1);
        root.left = t2;
        root.right = t3;
        t2.left = t4;
        t2.right = t5;
        return root;
    }

    public static void main(String[] args) {


        System.out.println(sumNumbers(case1()));


        System.out.println(sumNumbers(case2()));

    }



}
