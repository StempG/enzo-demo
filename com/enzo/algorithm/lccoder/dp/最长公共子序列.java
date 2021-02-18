package com.enzo.algorithm.lccoder.dp;

/**
 * https://leetcode-cn.com/problems/longest-common-subsequence/
 */
public class 最长公共子序列 {


    /**
     * https://leetcode-cn.com/problems/longest-common-subsequence/
     *
     * leetcode的题
     *
     */

    public static int longestCommonSubsequence(String text1, String text2) {

        /*
        定义int类型的  dp[i][j]表示 text1中前i个元素与text2中前j个元素 分别划分的子串 所存在最长公共子序列的长度。

        如果text1[i+1] 不等于 text2[j+1] 那么
        dp[i+1][j+1] = max(dp[i][j+1], dp[i+1][j])
        如果text1[i+1] 等于 text2[j+1] 那么
        dp[i+1][j+1] = dp [i][j] + 1;

         */

        int[][] dp = new int[text1.length() +1][text2.length()+1];

        for (int i = 0;i<text1.length();i++){
            for (int j = 0;j < text2.length();j++){
                if (text1.charAt(i) == text2.charAt(j)){
                    dp[i+1][j+1] = dp[i][j] + 1;
                }else {
                    dp[i+1][j+1] = Math.max(dp[i][j+1], dp[i+1][j]);
                }
            }
        }

        return dp[text1.length()][text2.length()];



    }


    /**
     * https://www.nowcoder.com/practice/6d29638c85bb4ffd80c020fe244baf11?tpId=117&&tqId=35014&&companyId=665&rp=1&ru=/company/home/code/665&qru=/ta/job-code-high/question-ranking
     *
     * 牛客的题
     */
    public static String LCS (String text1, String text2) {

        /*
       同 leetcode的题一样 （longestCommonSubsequence）
       这里的LCS只是需要记录输出这个公共的子序列即可
         */

        int[][] dp = new int[text1.length() +1][text2.length()+1];

        for (int i = 0;i<text1.length();i++){
            for (int j = 0;j < text2.length();j++){
                if (text1.charAt(i) == text2.charAt(j)){
                    dp[i+1][j+1] = dp[i][j] + 1;

                }else {
                    dp[i+1][j+1] = Math.max(dp[i][j+1], dp[i+1][j]);
                }
            }
        }

        StringBuilder result = new StringBuilder();
        int i = text1.length(), j = text2.length();
        while (i > 0 && j > 0) {
            if (text1.charAt(i-1) == text2.charAt(j-1)) {
                result.append(text1.charAt(i-1));
                i--;
                j--;
            } else {
                if (dp[i][j-1] > dp[i-1][j]) {
                    j--;
                } else {
                    i--;
                }
            }
        }

        if (result.length() <=0 ){
            return "-1";
        }

        return result.reverse().toString();
    }


    public static void main(String[] args) {

        int result1 = longestCommonSubsequence("abcde", "ace");
        System.out.println(result1);

        String result2 = LCS("1A2C3D4B56","B1D23CA45B6A");
        System.out.println(result2);


    }





}
