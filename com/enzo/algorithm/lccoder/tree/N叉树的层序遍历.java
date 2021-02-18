package com.enzo.algorithm.lccoder.tree;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;


//https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal/
//N叉树的层序遍历
public class N叉树的层序遍历 {

    private static class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }


    public static List<List<Integer>> levelOrder(Node root) {

        List<List<Integer>> result = new ArrayList<>();

        //递归遍历
        if (root != null){
            put(root, 0, result);
        }

        return result;
    }

    private static void put(Node node, int level,List<List<Integer>> result ){
        if (level >= result.size()){
            //初始化该层的list
            result.add(new ArrayList<>());
        }

        //开始放入node
        result.get(level).add(node.val);
        //遍历下一层，从左到右
        if (node.children != null){
            for (Node child :node.children){
                put(child, level+1, result);
            }
        }


    }


    public static void main(String[] args) {

        Node root = new Node(1);
        Node node3 = new Node(3);
        Node node2 = new Node(2);
        Node node4 = new Node(4);
        root.children = Lists.newArrayList(node3, node2, node4);

        Node node5 = new Node(5);
        Node node6 = new Node(6);

        node3.children =  Lists.newArrayList(node5, node6);

        System.out.println(levelOrder(root));

    }

}
