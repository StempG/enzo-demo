package com.enzo.algorithm.lccoder.sort;

import java.util.Arrays;
import java.util.Random;


/**
 * 归并排序
 */
public class 归并排序 {


    public static void main(String[] args) {

        int[] a = new Random().ints(20, 1,1000).toArray();

        mergeSort(a, 0, a.length-1);

        System.out.println(Arrays.toString(a));


    }




    public static void mergeSort(int[] array, int left, int right) {
       if (left < right) {
            int mid = (left+right) / 2;// 中间元素的index
            mergeSort(array, left, mid);//对左边区间再分裂
            mergeSort(array, mid+1, right);//对右边区间再分裂
            merge(array, left, mid, right);
        }
    }


    public static void merge(int[] a, int left, int mid, int right) {
        if (left >= right){
            return;
        }

        int[] tmp = new int[a.length];//辅助数组
        int p1 = left, p2 = mid + 1, k = left;//p1、p2是检测指针，k是存放指针

        //循环比较 mid 左右两个区间，直到有一边区间的值比对完毕
        while (p1 <= mid && p2 <= right) {
            if (a[p1] <= a[p2])
                tmp[k++] = a[p1++];
            else
                tmp[k++] = a[p2++];
        }


        //两个循环只会执行其中一个
        while (p1 <= mid) {
            tmp[k++] = a[p1++];//如果第一个序列未检测完，直接将后面所有元素加到合并的序列中
        }
        while (p2 <= right) {
            tmp[k++] = a[p2++];//同上
        }

        //复制回原数组
        for (int i = left; i <= right; i++) {
            a[i] = tmp[i];
        }
    }


}
