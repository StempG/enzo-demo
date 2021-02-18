package com.enzo.algorithm.lccoder;

import java.util.HashMap;
import java.util.Map;

public class So {


    public static void main(String[] args) {
        System.out.println(MoreThanHalfNum_Solution(new int[]{4,2,1,4,2,4}));
    }
    public static int MoreThanHalfNum_Solution(int [] nums) {
        if(nums ==null || nums.length<=0){
            return 0;
        }

        int length = (nums.length) / 2;

        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i : nums){
            Integer times = map.get(i);
            if(times ==null){
                times = 1;
            }else{
                times = times+1;
            }
            map.put(i, times);
        }

        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            if(entry.getValue() > length){
                return entry.getKey();
            }


        }

        return 0;
    }

}
