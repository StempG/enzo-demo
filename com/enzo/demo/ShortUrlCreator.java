package com.enzo.demo;


import java.security.MessageDigest;
import java.util.Random;

/**
 * 短链接生成类
 *
 * 2018/7/4
 */
public class ShortUrlCreator {



//
//
//    public static void main(String[] args) {
//
//        System.out.println(0x3FFFFFFF);
//
//        String sLongUrl = "http://www.51bi.com/bbs/_t_278433840/"; // 原始链接
//        System.out.println("长链接:"+sLongUrl);
//        String[] aResult = shortUrl(sLongUrl);//将产生4组6位字符串
//        // 打印出结果
//        for (int i = 0; i < aResult.length; i++) {
//            System.out.println("[" + i + "]:" + aResult[i]);
//        }
//        Random random=new Random();
//        int j=random.nextInt(4);//产成4以内随机数
//        System.out.println("短链接:"+aResult[j]);//随机取一个作为短链
//
//
//    }


    /**
     * 算法思路：
     *
     * 1、将给定的字符串（长链接） 先转换为32位的一个md5字符串。  比如该字符串用A表示
     *
     * 2、将上面的A字符串分为4段处理， 每段的长度为8 ， 比如四段分别为  M、N、O、P
     *
     * 3、可以将M字符串当作一个16进制格式的数字来处理， 将其转换为一个Long类型。  比如转换为L
     *
     * 4、此时L的二进制有效长度为32位， 需要将前面两位去掉，留下30位  ， 可以 & 0x3fffffff 得到想要的结果
     *
     * 5、此时L的二进制有效长度为30位， 分为6段处理， 每段的长度为5
     *
     * 6、依次取出L的每一段（5位），进行位操作 &  0x0000003D 得到一个 <= 61的数字，来当做index 。根据index 去预定义的字符表里面去取一个字符， 最后能取出6个字符，此时就能那这6个字符相加，成一个字符串。 作为短链接了。
     *
     * 7、根据2重复3、4、5、6 ，总共能得到6个第六步生成的字符串。
     *
     *
     * @param url url
     * @return 短连接备选
     */
    private static String[] shortUrl(String url) {
        // 可以自定义生成 MD5 加密字符传前的混合 KEY
        String key = "test";
        // 要使用生成 URL 的字符
        String[] chars = new String[] { "field", "b", "c", "d", "e", "f", "g", "h",
                "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
                "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H",
                "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                "U", "V", "W", "X", "Y", "Z"

        };
        // 对传入网址进行 MD5 加密
        String hex = md5ByHex(key + url);

        String[] resUrl = new String[4];
        for (int i = 0; i < 4; i++) {

            // 把加密字符按照 8 位一组 16 进制与 0x3FFFFFFF 进行位与运算
            String sTempSubString = hex.substring(i * 8, i * 8 + 8);

            // 这里需要使用 long 型来转换，因为 Inteper .parseInt() 只能处理 31 位 , 首位为符号位 , 如果不用long ，则会越界
            long lHexLong = 0x3FFFFFFF & Long.parseLong(sTempSubString, 16);
            StringBuilder outChars = new StringBuilder();
            for (int j = 0; j < 6; j++) {
                // 把得到的值与 0x0000003D 进行位与运算，取得字符数组 chars 索引
                // 为什么与 0x0000003D 来进行位与运算？？
                // 解释之一：因为0x0000003D 是16进制数，换算成十进制是61，正好对应String[] chars数组的下标最大值。
                long index = 0x0000003D & lHexLong;
                // 把取得的字符相加
                outChars.append(chars[(int) index]);
                // 每次循环按位右移 5 位
                // 为什么是5位？
                // 解释之一：5 位 * 6次循环 <=32位，若取值大于5位，则会影响最后一位字母的选择，（会始终是同样的字母）
                lHexLong = lHexLong >> 5;
            }
            // 把字符串存入对应索引的输出数组
            resUrl[i] = outChars.toString();
        }
        return resUrl;
    }




    /**
     * MD5加密(32位大写)
     */
    private static String md5ByHex(String src) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] b = src.getBytes();
            md.reset();
            md.update(b);
            byte[] hash = md.digest();
            StringBuilder hs = new StringBuilder();
            String stmp;
            for (byte aHash : hash) {
                stmp = Integer.toHexString(aHash & 0xFF);
                if (stmp.length() == 1)
                    hs.append("0").append(stmp);
                else {
                    hs.append(stmp);
                }
            }
            return hs.toString().toUpperCase();
        } catch (Exception e) {
            return "";
        }
    }



}
