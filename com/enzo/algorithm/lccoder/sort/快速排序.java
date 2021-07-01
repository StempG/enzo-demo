package com.enzo.algorithm.lccoder.sort;

import java.util.Arrays;


/**
 * 快速排序
 * 
 */
public class 快速排序 {


    public static void main(String[] args) {

//        int[] a = new Random().ints(20, 1,1000).toArray();

//        int[] a = new int[5];
//        a[0] = 1;
//        a[1] = 1;
//        a[2] = 3;
//        a[3] = 5;
//        a[4] = 7;
//
//
//

        int[] a = new int[]{-7,4,5,1,6,2,7,3,8,89};
        quickSort(a, 0, a.length -1);

        System.out.println(Arrays.toString(a));


    }


    private static void swap(int[] array, int l, int r){
        if (l ==r){
            return;
        }

        int temp = array[r];
        array[r] = array[l];
        array[l] = temp;
    }


    public static void quickSort(int[] array, int left, int right){

        if (left >=right){
            return;
        }

        int l = left;
        int r = right;
        int pviot = array[left];

        while (l<r){

            //先看右边，依次往左递减
            //为什么要先看右边？
            while (l<r && array[r]>=pviot){
                r--;
            }
            while (l<r && array[l]<=pviot){
                l++;
            }

            if (l < r){
                swap(array, l, r);
            }
        }

        swap(array, l, left);

        quickSort(array, left, l-1);
        quickSort(array, l+1, right);


    }





}
