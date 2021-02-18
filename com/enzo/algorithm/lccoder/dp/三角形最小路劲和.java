package com.enzo.algorithm.lccoder.dp;

import java.util.List;


/**
 *
 * 【120】 三角形最小路劲和
 * https://leetcode-cn.com/problems/triangle/
 *
 */
//给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
//
// 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
//
//
//
// 例如，给定三角形：
//
// [
//     [2],
//    [3,4],
//   [6,5,7],
//  [4,1,8,3]
//]
//
//
// 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
//
//
//
// 说明：
//
// 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
// Related Topics 数组 动态规划


//leetcode submit region begin(Prohibit modification and deletion)

public class 三角形最小路劲和 {


    public static void main(String[] args) {




    }


    public static int minimumTotal(List<List<Integer>> triangle) {
        // arr[i][j]存储的是从最底层( size+1 虚拟的层)到第i层第j个节点的累计路径和。
        int[][] arr = new int[triangle.size()+1][triangle.size()+1];


        for (int l = triangle.size() - 1;l>=0;l--){
            List<Integer> line =triangle.get(l);

            for (int index =0 ;index < line.size();index++){
                arr[l][index] = Math.min(arr[l+1][index], arr[l+1][index+1]) + line.get(index);
            }
        }


        return arr[0][0];
    }
}
