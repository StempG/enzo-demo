package com.enzo.algorithm.lccoder;

import java.util.*;


/**
 *  面试题 16.11. 跳水板
 * https://leetcode-cn.com/problems/diving-board-lcci/
 *
 */
//  你正在使用一堆木板建造跳水板。有两种类型的木板，其中长度较短的木板长度为shorter，
//  长度较长的木板长度为longer。你必须正好使用k块木板。编写一个方法，生成跳水板所有可能的长度。
//
//        返回的长度需要从小到大排列。
//
//        示例：
//
//        输入：
//        shorter = 1
//        longer = 2
//        k = 3
//        输出： {3,4,5,6}
//        提示：
//
//        0 < shorter <= longer
//        0 <= k <= 100000
//
//        来源：力扣（LeetCode）
//        链接：https://leetcode-cn.com/problems/diving-board-lcci
//        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class DivingBoard {


    /**
     * 超出时间限制的结果
     * @param shorter
     * @param longer
     * @param k
     * @return
     */
    public static int[] divingBoard(int shorter, int longer, int k) {
        if (k<=0){
            return new int[]{};
        }

        List<Integer> list = new ArrayList<>();
        for (int longN = 0; longN <= k; longN++){

            int l = longN * longer + (k-longN)* shorter;
            if (!list.contains(l)){
                list.add(l);
            }
        }
        if (list.isEmpty()){
            return new int[]{};
        }

        int[] a = new int[list.size()];
        int index = 0;
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()){
            Integer val = iterator.next();
            a[index] = val;
            index++;
        }

        return a;

    }



    public static void main(String[] args) {


        int[] ints = divingBoard(1, 1, 0);
        System.out.println(ints);


    }
}
