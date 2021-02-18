package com.enzo.algorithm.lccoder.dp;

/**
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 *
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 *
 *
 *
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-paths-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 不同路径2 {

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {

        /*
         此题可以用动态规划来解
         记左上角坐标[0,0]，右下角为坐标为[i,j]
         dp[i][j]表示 坐标[i][j]到右下角的路径数



         */

        int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];

        dp[0][0] = 1;
        dp[1][0] = obstacleGrid[1][0] == 1 ? 0: 1;
        dp[0][1] = obstacleGrid[0][1] == 1 ? 0: 1;

        for (int i = 1;i<obstacleGrid.length;i++){


            for (int j = 1;j<obstacleGrid[i].length;j++){

                if (obstacleGrid[i][j-1] + obstacleGrid[i-1][j] == 2){
                    dp[i][j] = 0;
                }

                if (obstacleGrid[i][j-1]  + obstacleGrid[i-1][j] == 0){
                    dp[i][j] = dp[i-1][j-1] +2;
                }
                if (obstacleGrid[i][j-1]  + obstacleGrid[i][j-1] == 1){ // 这里只要不同时为障碍物即可
                    dp[i][j] = dp[i-1][j-1];
                }

            }
        }

        return dp[obstacleGrid.length-1][obstacleGrid[0].length-1];

    }

    public static void main(String[] args) {
        int [][] a = new int[3][3];

        a[1][1] = 1;
        int i = uniquePathsWithObstacles(a);
        System.out.println(i);
    }


}
