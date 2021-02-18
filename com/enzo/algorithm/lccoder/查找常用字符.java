package com.enzo.algorithm.lccoder;

import java.util.ArrayList;
import java.util.List;

//https://leetcode-cn.com/problems/find-common-characters/
public class 查找常用字符 {



    private static boolean removeIfContains(StringBuffer str, char c){
        if (str == null || str.length()<=0){
            return false;
        }


        for (int i=0;i<str.length();i++){
            char ch = str.charAt(i);
            if (ch  == c ){
                str.deleteCharAt(i);
                return true;
            }
        }
        return false;
    }


    public static List<String> commonChars(String[] A) {
        if (A==null || A.length<=0){
            return new ArrayList<>();
        }

        List<String> result = new ArrayList<>();

        String base = A[0];



        for (char c:base.toCharArray()){

            boolean flag = true;
            for (int i=1;i<A.length;i++){

                StringBuffer str = new StringBuffer(A[i]);

                if (!removeIfContains(str, c)){
                    flag = false;
                    break;
                }else {
                    A[i] = str.toString();
                }
            }

            if (flag){
                result.add(String.valueOf(c));
            }
        }

        return result;


    }

    public static void main(String[] args) {
        String[] a = new String[]{"bella","label","roller"};

        System.out.println(commonChars(a));
    }



}
