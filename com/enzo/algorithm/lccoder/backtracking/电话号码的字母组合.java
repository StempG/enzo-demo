package com.enzo.algorithm.lccoder.backtracking;

import java.util.*;

//https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
public class 电话号码的字母组合 {


    static Map<Character, char[]> map = new HashMap<>();
    static {
        map.put('2', new char[]{'a','b','c'});
        map.put('3', new char[]{'d','e','f'});
        map.put('4', new char[]{'g','h','i'});
        map.put('5', new char[]{'j','k','l'});
        map.put('6', new char[]{'m','n','o'});
        map.put('7', new char[]{'p','q','r', 's'});
        map.put('8', new char[]{'t','u','v'});
        map.put('9', new char[]{'w','x','y','z'});

    }


    public static void main(String[] args) {
        System.out.println(letterCombinations("23"));
    }

    public static List<String> letterCombinations(String digits) {

        if (digits == null || digits.length()<=0){
            return new ArrayList<>();
        }


        List<String> result = new ArrayList<>();




        StringBuffer route = new StringBuffer();
            back(0, digits, route, result);


        return result;
    }


    public static void back(int indexOfDigits, String digits, StringBuffer route, List<String> result){
        if (indexOfDigits >= digits.length()){//所有的字母都已经遍历完，
            result.add(route.toString());//把路劲加入到结果集中
            return ;//回溯
        }

        char digit = digits.charAt(indexOfDigits);
        char[] letters = map.get(digit);

        for (char letter : letters) {
            route.append(letter);
            back(indexOfDigits + 1, digits, route, result);//下探
            route.deleteCharAt(indexOfDigits);//删除最后一层的字符，所以是indexOfDigits
        }

    }
}
