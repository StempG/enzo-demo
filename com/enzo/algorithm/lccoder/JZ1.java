package com.enzo.algorithm.lccoder;


//https://www.nowcoder.com/practice/abc3fe2ce8e146608e868a70efebf62e?tpId=13&&tqId=11154&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking


/*
在一个二维数组中（每个一维数组的长度相同），
每一行都按照从左到右递增的顺序排序，
每一列都按照从上到下递增的顺序排序。
请完成一个函数，
输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 */
public class JZ1 {


    public static void main(String[] args) {

    }



    public static String parse(StringBuffer str) {
        if (str == null ){
            return null;
        }
        if (str.length()<=0){
            return "";
        }

        return str.toString().replace(" ", "%20");


    }
}
