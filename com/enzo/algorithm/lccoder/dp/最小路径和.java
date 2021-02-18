package com.enzo.algorithm.lccoder.dp;

import java.util.Arrays;

//https://leetcode-cn.com/problems/minimum-path-sum/
public class 最小路径和 {


    public static int minPathSum(int[][] grid) {
        if (grid == null || grid.length<=0 || grid[0].length<=0){
            return 0;
        }


        //dp[m][n]表示到m,n方位的最小数字总和，令A(m,n)表示m,n方位的数值
        //dp[m][n] = Min(dp[m-1][n], dp[m][n-1]) + A(m,n)
        //边界值dp[0][0]=0

        int rowNum = grid.length;
        int columnNum = grid[0].length;

        int[][] dp = new int[rowNum+1][columnNum+1];

        Arrays.stream(dp).forEach(a-> Arrays.fill(a, Integer.MAX_VALUE));

        dp[0][0]=0;
        dp[0][1]=0;
        dp[1][0]=0;



        for (int i=0;i<rowNum;i++){
            for (int j =0;j<columnNum;j++){
                dp[i+1][j+1] = Math.min(dp[i][j+1], dp[i+1][j]) + grid[i][j];
            }
        }

        return dp[rowNum][columnNum];
    }

    public static void main(String[] args) {
        int[][] grid = new int[3][3];

        grid[0] = new int[]{1,3,1};
        grid[1] = new int[]{1,5,1};
        grid[2] = new int[]{4,2,1};

        System.out.println(minPathSum(grid));
    }
}
