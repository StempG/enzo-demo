package com.enzo.algorithm.lccoder.dp;

/**
 * https://www.nowcoder.com/practice/554aa508dd5d4fefbf0f86e5fe953abd?tpId=117&&tqId=35068&&companyId=665&rp=1&ru=/company/home/code/665&qru=/ta/job-code-high/question-ranking
 *
 * 题目描述
 * 给定一个数组arr，返回子数组的最大累加和
 * 例如，arr = [1, -2, 3, 5, -2, 6, -1]，所有子数组中，[3, 5, -2, 6]可以累加出最大的和12，所以返回12.
 * 题目保证没有全为负数的数据
 * [要求]
 * 时间复杂度为O(n)，空间复杂度为O(1)
 *
 * 示例1
 * 输入
 *
 * [1, -2, 3, 5, -2, 6, -1]
 * 返回值
 *
 * 12
 */
public class 子数组的最大累加和问题 {


    public static int maxsumofSubarray (int[] arr) {


        //状态定义：
        //令dp[i]表示前以arr[i]为末尾的连续子数组的累加的最大和的值。
        //状态转移
        //dp[i] = max(0, dp[i-1]) + arr[i]  因为当dp[i-1]<0的时候，还不如直接使用单个arr[i]是作为子数组。
        //初始状态
        //dp[0]=  arr[0]


        int[] dp = new int[arr.length];
        int max = arr[0];
        dp[0] = arr[0];
        for (int i = 1; i<arr.length;i++){
            dp[i] = Math.max(0, dp[i-1]) + arr[i];
            max = Math.max(max, dp[i]);
        }

        return max;
    }


    public static void main(String[] args) {

        int result = maxsumofSubarray(new int[]{1, -2, 3, 5, -2, 6, -1});
        System.out.println(result);
    }


}
