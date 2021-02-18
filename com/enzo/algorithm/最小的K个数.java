package com.enzo.algorithm;

import java.util.ArrayList;

public class 最小的K个数 {



    private static void swap(int[] array, int l, int r){
        int temp = array[r];
        array[r] = array[l];
        array[l] = temp;
    }


    public static void quickSort(int[] array, int left, int right){

        if (left >=right){
            return;
        }

        int p = left;
        int r = right;
        int pviot = array[left];




        while (left<right){


            while (left<right && array[left]<=pviot){
                left++;
            }
            while (left<right && array[right]>=pviot){
                right--;
            }


            swap(array, left, right);

        }

        swap(array, p, left);

        quickSort(array, p, left-1);
        quickSort(array, left+1, r);


    }

    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {

        quickSort(input, 0, input.length-1);


        ArrayList<Integer> result = new ArrayList<>(k);
        for (int i=0;i<k;i++){
            result.add(input[i]);
        }
        return result;



    }

}
