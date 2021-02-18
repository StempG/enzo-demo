package com.enzo.demo;


import java.util.Scanner;

/**
 * @version 2019
 */
public class TaxCalculator {


    /**
     * 最低社保基数
     */
    private static final double MIN_SHEBAO_BASELINE = 3054.95;
    /**
     * 最高社保基数
     */
    private static final double MAX_SHEBAO_BASELINE = 15274.74;

    /**
     * 最低公积金基数
     */
    private static final double MIN_GONGJIJIN_BASELINE = 1860.00;
    /**
     * 最高公积金基数
     */
    private static final double MAX_GONGJIJIN_BASELINE = 21980.00;


    /**
     * （老板方） 养老金缴纳比例
     */
    private static final double EMPLOYER_RATE_OF_YANGLAOJIN = 0.14;
    /**
     * （搬砖方） 养老金缴纳比例
     */
    private static final double EMPLOYEE_RATE_OF_YANGLAOJIN = 0.08;

    /**
     * （老板方） 医保缴纳比例
     */
    private static final double EMPLOYER_RATE_OF_MED = 0.105;
    /**
     * （搬砖方） 养老金缴纳比例
     */
    private static final double EMPLOYEE_RATE_OF_MED = 0.02;

    /**
     * 公积金比例
     */
    private static final double RATE_OF_GONGJIJIN = 0.12;


    private static final int BASE_TAX_LINE = 5000;

//    /**
//     * （老板方） 养老金缴纳比例
//     */
//    private static final double EMPLOYER_RATE_OF_YANGLAOJIN = 0.14;
//    /**
//     * （搬砖方） 养老金缴纳比例
//     */
//    private static final double EMPLOYEE_RATE_OF_YANGLAOJIN = 0.08;
//
//    /**
//     * （老板方） 养老金缴纳比例
//     */
//    private static final double EMPLOYER_RATE_OF_YANGLAOJIN = 0.14;
//    /**
//     * （搬砖方） 养老金缴纳比例
//     */
//    private static final double EMPLOYEE_RATE_OF_YANGLAOJIN = 0.08;


    public static void main(String[] args) {


        boolean mark = true;
        while (mark) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("输入月薪：");
            double mAvg = scanner.nextDouble();

            System.out.print("专项扣除金额：");
            double youhui = scanner.nextDouble();

            System.out.println("是否按照标准： 1-按照标准， 2-按照最低金额 3-自定义金额");
            double standard = scanner.nextDouble();

            double baseS;
            double baseG;

            if (standard == 1 || standard == 2) {
                baseS = standard == 1 ? Math.min(Math.max(MIN_SHEBAO_BASELINE, mAvg), MAX_SHEBAO_BASELINE) : MIN_SHEBAO_BASELINE;
                baseG = standard == 1 ? Math.min(Math.max(MIN_GONGJIJIN_BASELINE, mAvg), MAX_GONGJIJIN_BASELINE) : MIN_GONGJIJIN_BASELINE;


            } else {
                System.out.print("请输入社保缴纳标准：");
                double shebao = scanner.nextDouble();
                System.out.print("请输入公积金缴纳标准：");
                double gongjijin = scanner.nextDouble();

                baseS = Math.min(Math.max(MIN_SHEBAO_BASELINE, shebao), MAX_SHEBAO_BASELINE);
                baseG = Math.min(Math.max(MIN_GONGJIJIN_BASELINE, gongjijin), MAX_GONGJIJIN_BASELINE);

            }


            //养老金
            double yanglaojin_EE = baseS * EMPLOYEE_RATE_OF_YANGLAOJIN;
            double yanglaojin_ER = baseS * EMPLOYER_RATE_OF_YANGLAOJIN;
            System.out.println("个人养老金: " + yanglaojin_EE);

            //医保
            double yibao_EE = baseS * EMPLOYEE_RATE_OF_MED;
            double yibao_ER = baseS * EMPLOYER_RATE_OF_MED;
            System.out.println("个人医保: " + yibao_EE);

            //公积金
            double gongjijin_EE = baseG * RATE_OF_GONGJIJIN;
            double gongjijin_ER = baseG * RATE_OF_GONGJIJIN;
            System.out.println("个人公积金: " + gongjijin_EE);

            double tax = tax(mAvg, (yanglaojin_EE + yibao_EE + gongjijin_EE + youhui));
            System.out.println("个税缴纳：" + tax);


            double afterTax = mAvg - tax - gongjijin_EE - yanglaojin_EE - yibao_EE;

            double eeRealPay = mAvg + gongjijin_ER + yibao_ER + yanglaojin_ER;
            System.out.println("个人税后所得：" + afterTax);
            System.out.println("公司实际支出:" + eeRealPay);

            System.out.println("=====================================");
            System.out.println("还要算吗？ 1-算 2-退出");
            int i = scanner.nextInt();

            mark = i == 1;
        }


    }


    private static double tax(double avg, double minus) {
        double base = avg - minus - BASE_TAX_LINE;
        if (base <= 3000) {
            return base * 0.03;
        } else if (base > 3000 && base <= 12000) {
            return base * 0.1 - 210;
        } else if (base > 12000 && base <= 25000) {
            return base * 0.2 - 1410;
        } else if (base > 25000 && base <= 35000) {
            return base * 0.25 - 2660;
        } else if (base > 35000 && base <= 55000) {
            return base * 0.3 - 4410;
        } else if (base > 55000 && base <= 80000) {
            return base * 0.35 - 7160;
        } else if (base > 80000) {
            return base * 0.45 - 15160;
        }

        throw new RuntimeException("无法计算出个税，当前月薪为:" + avg);
    }
}
