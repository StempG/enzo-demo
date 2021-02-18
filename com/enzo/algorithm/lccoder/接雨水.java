package com.enzo.algorithm.lccoder;

import java.util.Arrays;


/**
 * 【42】 接雨水
 * https://leetcode-cn.com/problems/trapping-rain-water/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-8/
 */
//给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
//
//
//
// 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Mar
//cos 贡献此图。
//
// 示例:
//
// 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
//输出: 6
// Related Topics 栈 数组 双指针


public class 接雨水 {


    public static void main(String[] args) {


        int[] a = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};

//        System.out.println(countDropsByRow(a));

        System.out.println(countDropsByColumn(a));

    }


    /**
     * 按照列来求总的水滴
     *
     *
     * 遍历数组
     *
     * 向左蓄水与向右蓄水
     * 从左边开始遍历
     * 找到第一个非0的列，记为h1，继续遍历，如果列高h2 < h1,则可以蓄水，如果h2>= h1,则蓄水结束，并且将h1置为h2，继续遍历
     *
     * @param array
     * @return
     */
    private static int countDropsByColumn(int[] array){
        int sum = 0;
        //最两端的列不用考虑，因为一定不会有水。所以下标从 1 到 length - 2
        for (int i = 1; i < array.length - 1; i++) {
            int max_left = 0;
            //找出左边最高
            for (int j = i - 1; j >= 0; j--) {
                if (array[j] > max_left) {
                    max_left = array[j];
                }
            }
            int max_right = 0;
            //找出右边最高
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] > max_right) {
                    max_right = array[j];
                }
            }
            //找出两端较小的
            int min = Math.min(max_left, max_right);
            //只有较小的一段大于当前列的高度才会有水，其他情况不会有水
            if (min > array[i]) {
                sum = sum + (min - array[i]);
            }
        }
        return sum;


    }




    /**
     * 按照每一行来求总的水滴
     * @param array
     * @return
     */
    private static int countDropsByRow(int[] array) {

        int height = getHeight(array);


        int totalDrops = 0;
        for (int high = 1; high <= height; high++) {

            int levelDropsCount = 0;


            boolean leftWall = false;
            boolean countMode = false;
            int temp = 0;
            for (int i = 0; i < array.length; i++) {


                //遇到第一个墙
                if (array[i] >= high && !leftWall) {
                    leftWall = true;
                    countMode = true;
                    continue;
                }

                //开始计算
                if (countMode && array[i] < high) {
                    temp++;
                    continue;
                }


                //遇到右边的墙
                if (countMode && array[i] >= high) {
                    levelDropsCount += temp;
                    temp = 0;
                }
            }

            totalDrops += levelDropsCount;

        }

        return totalDrops;


    }

    private static int getHeight(int[] array) {

        if (array == null || array.length <=0){
            return 0;
        }

        return Arrays.stream(array).max().getAsInt();

    }


}
