package com.enzo.algorithm.lccoder.dp;

/**
 * https://www.nowcoder.com/practice/f33f5adc55f444baa0e0ca87ad8a6aac?tpId=117&&tqId=35268&&companyId=665&rp=1&ru=/company/home/code/665&qru=/ta/job-code-high/question-ranking
 *
 * 给定两个字符串str1和str2,输出两个字符串的最长公共子串，如果最长公共子串为空，输出-1。
 *
 * 示例1
 * "1AB2345CD","12345EF"
 *
 * 返回值
 * "2345"
 */
public class 最长公共子串 {


    /**
     * 思路可以参看 https://blog.csdn.net/ggdhs/article/details/90713154
     *
     *
     */
    public static String LCS (String str1, String str2) {

        if (str1.length()<=0|| str2.length() <=0){
            return "-1";
        }


        //令dp[i][j] 表示 str1[i]，str2[j]为结尾的最长公共子串的长度
        //递推
        //str1[i] == str2[j] ，dp[i][j] = dp[i-1][j-1] + 1
        //str1[i] != str2[j] ，dp[i][j] = 0;
        //初始值
        //dp[0][0] = 0;

        int[][] dp = new int[str1.length()+1][str2.length()+1];
        int maxLength = 0;
        int maxIndex = 0;
        for (int i =0;i<str1.length();i++){
            for (int j = 0;j<str2.length();j++){
                if (str1.charAt(i) == str2.charAt(j)){
                    dp[i+1][j+1] = dp[i][j] + 1;
                    if (dp[i+1][j+1] > maxLength){
                        maxLength = dp[i+1][j+1];
                        maxIndex = i;
                    }
                }else {
                    dp[i+1][j+1] = 0;
                }
            }
        }


        if (maxLength ==0){
            return "-1";
        }

        System.out.println(maxLength);


        return str1.substring( maxIndex+1 - maxLength,maxIndex+1);




    }


    public static void main(String[] args) {

        System.out.println(LCS("1AB2345CD","12345EF"));


    }
}
