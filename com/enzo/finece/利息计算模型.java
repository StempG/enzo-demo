package com.enzo.finece;

import java.util.HashMap;
import java.util.Map;

public class 利息计算模型 {

    static double 本金总额 = 1040000d;


    static Map<Integer, Double> 每月本金应还 = new HashMap<>(360);
    static Map<Integer, Double> 每月利息应还 = new HashMap<>(360);


    static double 方案二的年化利率 = 6/1000d;//千分之六
    static double 方案二的年还款总额 = 72000d;
    static int 方案二从第N个月开始执行 = 24;
    static int 方案二贷款存续周期_年 = 2;

    public static void main(String[] args) {
        等额本息();
        方案二();
    }

    private static void 方案二(){
        double 剩余本金 = 本金总额;

        for (int i = 1; i<= 方案二从第N个月开始执行;i++){
            剩余本金 = 剩余本金 - 每月本金应还.get(i);
        }

        System.out.println("第"+方案二从第N个月开始执行 + "个月后，剩余待还本金还有：" + 剩余本金 + "元");

        double 月利息 = 剩余本金 * 方案二贷款存续周期_年 * 方案二的年化利率 / 12;


        double 等额本息方案剩余本金 = 本金总额;
        double 等额本息方案累计利息 = 0;
        double 方案二剩余本金 = 本金总额;
        double 方案二累计利息 = 0;
        int start=0;
        for (int i=1;i<361;i++){
            等额本息方案累计利息  = 等额本息方案累计利息 + 每月利息应还.get(i);
            等额本息方案剩余本金 = 等额本息方案剩余本金 - 每月本金应还.get(i);



            if (i >= 方案二从第N个月开始执行 ){

                start++;

                if (start>方案二贷款存续周期_年*12){
                    月利息 = 方案二剩余本金 * 方案二贷款存续周期_年 * 方案二的年化利率 / 12;
                    start = 0;
                }

                if (方案二剩余本金>0){
                    方案二累计利息 = 方案二累计利息 + 月利息;
                    方案二剩余本金 = 方案二剩余本金 - (方案二的年还款总额/12 - 月利息);
                }else {
                    break;
                }
            }else{
                方案二累计利息  = 方案二累计利息 + 每月利息应还.get(i);
                方案二剩余本金 = 方案二剩余本金 - 每月本金应还.get(i);
            }

            System.out.println("当前第" + i+"个月: " + "等额本息方案支出利息共" + 等额本息方案累计利息 +"   剩余本金为" + 等额本息方案剩余本金);
            System.out.println("当前第" + i+"个月: " + "方案二支出利息共" + 方案二累计利息 +"   剩余本金为" + 方案二剩余本金 + "，当前月息为"+月利息);

        }

    }


    private static void 等额本息() {
        double yearRate = 5.5/100;
        double monthRate = yearRate/12;
        int year = 30;
        int month = year * 12;
        // 每月本息金额  = (本金×月利率×(1＋月利率)＾还款月数)÷ ((1＋月利率)＾还款月数-1)
        double monthIncome = (本金总额 * monthRate * Math.pow(1+monthRate,month))/(Math.pow(1+monthRate,month)-1);
//        System.out.println("每月本息金额 : " + monthIncome);
//        System.out.println("---------------------------------------------------");
        // 每月本金 = 本金×月利率×(1+月利率)^(还款月序号-1)÷((1+月利率)^还款月数-1)
        double monthCapital = 0;


        for(int i=1;i<month+1;i++){
            monthCapital = (本金总额 * monthRate * (Math.pow((1+monthRate),i-1)))/(Math.pow(1+monthRate,month)-1);
//            System.out.println("第" + i + "月本金： " + monthCapital);
            每月本金应还.put(i, monthCapital);
        }
//        System.out.println("---------------------------------------------------");
        // 每月利息  = 剩余本金 x 贷款月利率
        double monthInterest = 0;
        double capital = 本金总额;
        double tmpCapital = 0;
        for(int i=1;i<month+1;i++){
            capital = capital - tmpCapital;
            monthInterest = capital * monthRate;
            tmpCapital = (本金总额 * monthRate * (Math.pow((1+monthRate),i-1)))/(Math.pow(1+monthRate,month)-1);
//            System.out.println("第" + i + "月利息： " + monthInterest);
            每月利息应还.put(i, monthInterest);

        }
    }
}
