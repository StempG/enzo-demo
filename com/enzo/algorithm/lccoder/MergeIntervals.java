package com.enzo.algorithm.lccoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 *
 * 【56】 合并区间
 * https://leetcode-cn.com/problems/merge-intervals/
 *
 */
//给出一个区间的集合，请合并所有重叠的区间。
//
// 示例 1:
//
// 输入: [[1,3],[2,6],[8,10],[15,18]]
//输出: [[1,6],[8,10],[15,18]]
//解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
//
//
// 示例 2:
//
// 输入: [[1,4],[4,5]]
//输出: [[1,5]]
//解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
// Related Topics 排序 数组

public class MergeIntervals {


    public static void main(String[] args) {

        int[][] arr = new int[4][2];
        arr[0][0] = 1;arr[0][1] = 3;
        arr[1][0] = 2;arr[1][1] = 6;
        arr[2][0] = 8;arr[2][1] = 10;
        arr[3][0] = 15;arr[3][1] = 18;


        System.out.println(Arrays.deepToString(merge(arr)));
    }



    public static int[][] merge(int[][] intervals) {
        if (intervals.length<=1){
            return intervals;
        }


        //先对二维数组进行排序，按照内部数组的首个值的大小进行排序。
        //遍历数组，进行重叠区间判断

        quickSort(intervals, 0, intervals.length-1);

        List<List<Integer>> lists = new ArrayList<>();

        boolean merge = false;
        int formerLeft = intervals[0][0];
        int formerRight = intervals[0][1];
        for (int i =1;i<intervals.length;i++){
            int[] current = intervals[i];

            merge = current[0] <= formerRight;

            if (merge){
                formerRight = Math.max(formerRight,current[1]);
            }else {
                List<Integer> record = new ArrayList<>();
                record.add(formerLeft);
                record.add(formerRight);
                lists.add(record);


                formerLeft = current[0];
                formerRight = current[1];

            }

        }

        //处理最后一个数组元素，如果处于非merge mode
        List<Integer> record = new ArrayList<>();
        record.add(formerLeft);
        record.add(formerRight);
        lists.add(record);


        int[][] result = new int[lists.size()][2];
        for (int i = 0; i<lists.size();i++){
            result[i][0] = lists.get(i).get(0);
            result[i][1] = lists.get(i).get(1);
        }

        return result;


    }


    private static void quickSort(int[][] arr, int left, int right){
        if (left>=right){
            return;
        }
        int r = right;
        int l = left;
        int pviot = arr[left][0];

        while (l<r){
            while (l<r && arr[r][0] >= pviot){
                r--;
            }
            while (l<r && arr[l][0]<=pviot){
                l++;
            }



            swap(arr, l, r);
        }

        //交换pviot 跟l指针
        swap(arr, left, l);

        quickSort(arr, left,l-1);
        quickSort(arr, l+1, right);
    }

    private static void swap(int[][] arr, int l, int r){
        int[] temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;

    }

}
