package com.enzo.algorithm.lccoder.dp;

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 注意：给定 n 是一个正整数。
 *
 * 示例 1：
 *
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 示例 2：
 *
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/climbing-stairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 爬楼梯 {

    public static void main(String[] args) {


        System.out.println(climbStairs(3));




    }


    public static int climbStairs(int n) {
        //思路
        //爬上第N阶，有两种路子
        //第一种：在N-1阶，再爬1阶
        //第二种：在N-2阶，再爬2阶
        //所以，令dp[n] = 爬上第N阶梯的路线种类数量。
        //那么有 dp[n] = dp[n-1] + dp[n-2]
        //初始状态 dp[0]=1, dp[1]=1;

        if (n<=1){
            return 1;
        }
        if (n<=2){
            return 2;
        }
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1]= 1;
        for (int i= 2;i<=n;i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];

    }



}
