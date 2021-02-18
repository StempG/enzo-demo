package com.enzo.algorithm.lccoder.dp;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 示例 1：
 *
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 *
 * 输入: "cbbd"
 * 输出: "bb"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 最长回文子串 {





    public static String longestPalindrome(String s){

        if (s.length()<=1){
            return s;
        }

        //参考下面这个链接来进行理解分析
        //https://leetcode-cn.com/problems/longest-palindromic-substring/solution/zhong-xin-kuo-san-fa-he-dong-tai-gui-hua-by-reedfa/

        //以下是自己尝试写的代码
        boolean[][] dp = new boolean[s.length()][s.length()];
        int maxLength = 1;//初始情况下，最长的子串就是单个字符。
        int maxLeft = 0;
        int maxRight = 0;


        for (int right = 1; right < s.length();right++){//1.尝试从index=1 开始进行对字符串遍历

            for (int left = 0; left < right;left++){//2.设置左边界为0，表示在一个循环里，判断的字符串子串是在[left,right]之间


                //3.这里的判断是，看[left+1,right-1]这个区间是不是一个回文子串
                //但是需要考虑到边界值的问题。
                //
                //
                //
                //初始情况下，有3种情况
                //1.单个字符，比如dp[0][0], dp[1][1] 这样的对角线上的值，一定是true
                //2.另外如果子串的长度是2，那么只要看这个子串的两个字符是否相等，相等的话也初始成true，如aa
                //3.还有就是子串长度为3，那么只需要判断首尾是否相同即可，如aba
                //综合2.3的情况。可以得出一个判断条件为 right-left<=2
                boolean formerSub = dp[left+1][right-1] || (right-left<=2);
                if (s.charAt(left) == s.charAt(right) && formerSub){

                    //那么这个是一个回文串
                    dp[left][right] = true;

                    if (right - left + 1 > maxLength){
                        //记录下标
                        maxLeft = left;
                        maxRight = right;
                        maxLength = right - left + 1;
                    }
                }
            }

        }


        return s.substring(maxLeft, maxRight+1);

    }


    public static void main(String[] args) {

        System.out.println(longestPalindrome("babad"));


    }
}
