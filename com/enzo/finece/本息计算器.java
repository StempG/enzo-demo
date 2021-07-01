package com.enzo.finece;

import java.util.Scanner;

public class 本息计算器 {

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.println("请输入期初的借贷本金总金额：");
            int 本金金额 = scanner.nextInt();
            System.out.println("请输入利率：");
            double 利率= scanner.nextDouble();
            System.out.println("请输入每期偿还的本金：");
            int 每期偿还本金= scanner.nextInt();

            System.out.println("=========计算结果如下========");
            double 利息总额= 0;
            int 剩余本金金额 = 本金金额;
            int i = 0;
            int 已归还本金 = 0;
            for (;i<36;i++){
                if (剩余本金金额<=0){
                    已归还本金 = 本金金额;
                    break;
                }

                double 本期利息 = 剩余本金金额 * 利率 / 12;
                利息总额 = 利息总额 + 本期利息;
                已归还本金 = 已归还本金 + 每期偿还本金;
                剩余本金金额 = 本金金额 - 已归还本金;

            }
            System.out.println("总期数：" + (i));

            System.out.println("产生利息总额为：" + 利息总额);
            System.out.println("已归还本金总额为：" + 已归还本金);
            System.out.println("剩余待还本金为：" + 剩余本金金额);

            System.out.println();
            System.out.println();
            System.out.println();

        }
    }
}
