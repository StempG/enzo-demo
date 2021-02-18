package com.enzo.algorithm.lccoder;


//
//https://leetcode-cn.com/problems/find-majority-element-lcci/
public class Solution_167 {


    public static void main(String[] args) {
        int[] a = new int[]{3,2};
        System.out.println(majorityElement(a));
    }

    public static int majorityElement(int[] nums) {
        if (nums == null || nums.length<1){
            return  -1;
        }

        Integer target = null;
        int count =0;
        for (int num : nums) {
            if (target == null) {
                target = num;
                count++;
                continue;
            }

            if (target == num) {
                count++;
            } else {
                count--;
            }

            if (count == 0) {
                target = null;
            }

        }

        if (target == null){
            return -1;
        }

        return target;

    }
}
