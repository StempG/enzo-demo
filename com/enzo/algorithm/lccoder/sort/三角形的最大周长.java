package com.enzo.algorithm.lccoder.sort;

import java.util.Arrays;

public class 三角形的最大周长 {

    public static void main(String[] args) {
        System.out.println(largestPerimeter(new int[]{2,1,2}));
    }

    public static int largestPerimeter(int[] A) {

        if(A==null || A.length<3){
            return 0;
        }

        Arrays.sort(A);
        for (int i = A.length - 3; i >= 0; --i){
            if(A[i+2] + A[i+1] > A[i]){
                return A[i+2] + A[i+1] + A[i];
            }

        }

        return 0;
    }
}

