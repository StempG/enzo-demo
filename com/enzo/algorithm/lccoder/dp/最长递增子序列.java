package com.enzo.algorithm.lccoder.dp;

import java.util.Arrays;

/**
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * <p>
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [0,1,0,3,2,3]
 * 输出：4
 * <p>
 * <p>
 * <p>
 * 示例 3：
 * <p>
 * 输入：nums = [7,7,7,7,7,7,7]
 * 输出：1
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 最长递增子序列 {


    /**
     * https://leetcode-cn.com/problems/longest-increasing-subsequence
     */
    public static int lengthOfLIS(int[] nums) {


        //先使用dp的思路求得一遍解再说
        //定义dp[i] 表示 下标为i的数字之前的子数组的最长上升序列长度
        //那么 对于i来说，如果num[i] 比 num[0..i-1]都大，那么dp[i] = dp[i-1]+1


        //边界判断
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length <= 1) {
            return 1;
        }


        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);//每一个元素都是单个上升的子序列。所以dp[]的每个元素都可以初始化为1
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }


        int maxLisLength = 0;
        for (int j : dp) {
            maxLisLength = Math.max(maxLisLength, j);
        }
        return maxLisLength;

    }


    /**
     * https://www.nowcoder.com/practice/9cf027bf54714ad889d4f30ff0ae5481?tpId=117&&tqId=35013&&companyId=665&rp=1&ru=/company/home/code/665&qru=/ta/job-code-high/question-ranking
     * <p>
     * 要求输出字典序最小的递增序列
     */

    public static int[] LIS(int[] nums) {


        //边界判断
        if (nums.length <= 1) {
            return nums;
        }


        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);//每一个元素都是单个上升的子序列。所以dp[]的每个元素都可以初始化为1
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }


        int maxLisLength = 0;
        for (int j : dp) {
            maxLisLength = Math.max(maxLisLength, j);
        }


        //这里体现的是贪心算法
        //why ？？？
        int[] result = new int[maxLisLength];

        for (int i = dp.length - 1;i>=0;--i){
            if (dp[i] == maxLisLength){
                result[maxLisLength-1] = nums[i];
                --maxLisLength;
            }
        }

        return result;
    }

    public static void main(String[] args) {

        System.out.println(lengthOfLIS(new int[]{1, 3, 6, 7, 9, 4, 10, 5, 6}));

        System.out.println(Arrays.toString(LIS(new int[]{2, 1, 5, 3, 6, 4, 8, 9, 7})));


    }
}
