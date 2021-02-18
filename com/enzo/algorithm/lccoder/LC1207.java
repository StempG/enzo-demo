package com.enzo.algorithm.lccoder;

import java.util.*;


/**
 * https://leetcode-cn.com/problems/unique-number-of-occurrences/solution/du-yi-wu-er-de-chu-xian-ci-shu-by-leetcode-solutio/
 *
 *
 */
public class LC1207 {


    public static void main(String[] args) {

        System.out.println(uniqueOccurrences(new int[]{1,2,2,1,1,3}));


    }

    public static boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> numCountMap = new HashMap<>();

        for (int num : arr){
            Integer count = numCountMap.get(num);
            if (count == null){
                count = 0;
            }
            count = count+1;
            numCountMap.put(num, count);
        }

        Set<Integer>  set = new HashSet<>();
        numCountMap.forEach((k,v)-> set.add(v));


        return set.size() == numCountMap.size();
    }



}
