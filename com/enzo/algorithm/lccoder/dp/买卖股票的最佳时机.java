package com.enzo.algorithm.lccoder.dp;

/**
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
 *
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 *
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 *
 * 注意：你不能在买入股票前卖出股票。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 * 示例 2:
 *
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 买卖股票的最佳时机 {


    public static void main(String[] args) {

    }

    //暴力解法，但是leetcode上超时
    public static int maxProfit(int[] prices) {
        int maxProfit = 0;
        for (int i = 0;i<prices.length;i++){
            for (int j =i;j<prices.length;j++ ){
                maxProfit = Math.max(maxProfit, prices[j] - prices[i]);
            }

        }
        return maxProfit;
    }


    //dp解法
    //https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/solution/gu-piao-wen-ti-python3-c-by-z1m/
    public static int maxProfit_dp(int[] prices) {
        //思路，在最低点买入，然后遍历中查看当前价格的与最低点的差值（利润）是否超过最大利润
        int minPrice = prices[0];//初始设置
        int maxProfit = 0;
        for (int price : prices) {

            int currentProfit = price - minPrice;
            if (price < minPrice) {
                minPrice = price;
            } else if (currentProfit > maxProfit) {
                maxProfit = currentProfit;
            }

        }

        return maxProfit;





    }






}
