package com.enzo.finece;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeMap;

public class 本息计算器2 {


    @Data
    @AllArgsConstructor
    static class 模型 {

        private BigDecimal 本息;
        private BigDecimal 利息;
        private BigDecimal 本金;
        private BigDecimal 剩余本金;
    }

    /**
     * 获取每月本息金额
     * 计算方式
     * 每月本息金额  = (本金×月利率×(1＋月利率)＾还款月数)÷ ((1＋月利率)＾还款月数-1))
     *
     * @return 每月本息金额
     */
    public static double getMonthIncome(BigDecimal 替代金额) {
        double invest = 1020301.32 - 替代金额.doubleValue();
        double monthRate = 5.5 / 1200;   //月利率
        return (invest * monthRate * Math.pow(1 + monthRate, 343)) / (Math.pow(1 + monthRate, 343) - 1);
    }

    private static TreeMap<Integer, 模型> 等额本息模型(BigDecimal 替代金额) {
        BigDecimal 负债本金 = new BigDecimal("1020301.32").subtract(替代金额);
        BigDecimal 本息 = new BigDecimal(getMonthIncome(替代金额)).setScale(2, RoundingMode.HALF_UP);
        int 期数 = 1;

        TreeMap<Integer, 模型> 等额本息map = new TreeMap<>();

        BigDecimal 剩余本金 = 负债本金;
        while (剩余本金.doubleValue() > 0) {
            BigDecimal 利息 = 剩余本金
                    .multiply(new BigDecimal("5.5"))
                    .divide(new BigDecimal("1200"), 2, RoundingMode.HALF_UP);
            BigDecimal 本金 = 本息.subtract(利息).setScale(2, RoundingMode.HALF_UP);
            等额本息map.put(期数, new 模型(本息, 利息, 本金, 剩余本金));
            剩余本金 = 剩余本金.subtract(本金).setScale(2, RoundingMode.HALF_UP);


            期数++;

        }


//        等额本息map.forEach((K, V) -> System.out.println("期数" + K + "    " + V.toString()));
        return 等额本息map;
    }

    private static TreeMap<Integer, 模型> 消费贷模型(BigDecimal 起始本金, BigDecimal 每期还款本金, String 年利率, int 起始期数) {
        BigDecimal 剩余本金 = 起始本金;
        int 期数 = 1;

        TreeMap<Integer, 模型> map = new TreeMap<>();

        while (剩余本金.doubleValue() > 0) {


            BigDecimal 利息 = 剩余本金
                    .multiply(new BigDecimal(年利率))
                    .divide(new BigDecimal("1200"), 2, RoundingMode.HALF_UP);


            if (期数>=起始期数){
                BigDecimal 本息 = 利息.add(每期还款本金).setScale(2, RoundingMode.HALF_UP);
                剩余本金 = 剩余本金.subtract(每期还款本金);

                map.put(期数, new 模型(本息, 利息, 每期还款本金, 剩余本金));

            }else {
                map.put(期数, new 模型(利息, 利息, new BigDecimal(0), 剩余本金));
            }




            期数++;
        }

//        map.forEach((K, V) -> System.out.println("期数" + K + "    " + V.toString()));


        return map;

    }


    @Data
    @AllArgsConstructor
    static class 结果 {


        private BigDecimal 还款本息总额;

        private BigDecimal 剩余待还本金;

        private BigDecimal 付出利息;

        private int 期数;

        private BigDecimal 结余;

    }


    private static int 每期收入 = 21000;

    private static int 每期还款本金 = 10000;

    private static 结果 方案_不换房贷(int 限制期数) {

        TreeMap<Integer, 模型> 等额本息模型 = 等额本息模型(new BigDecimal(0));
        TreeMap<Integer, 模型> 利率4_35 = 消费贷模型(new BigDecimal(200000), new BigDecimal(每期还款本金), "4.35", 1);

        int 期数 = 1;

        BigDecimal 还款本息总额 = new BigDecimal(0);
        BigDecimal 剩余待还本金 = new BigDecimal(0);
        BigDecimal 付出利息 = new BigDecimal(0);
        BigDecimal 结余 = new BigDecimal(0);


        while (期数<=限制期数 &&
                (Objects.nonNull(等额本息模型.get(期数))
                        || Objects.nonNull(利率4_35.get(期数)))) {
            结余 = 结余.add(new BigDecimal(每期收入));


            还款本息总额 = 还款本息总额
                    .add(Optional.ofNullable(等额本息模型.get(期数)).map(模型::get本息).orElse(new BigDecimal(0)))
                    .add(Optional.ofNullable(利率4_35.get(期数)).map(模型::get本息).orElse(new BigDecimal(0)));
            剩余待还本金 = new BigDecimal(0)
                    .add(Optional.ofNullable(等额本息模型.get(期数)).map(模型::get剩余本金).orElse(new BigDecimal(0)))
                    .add(Optional.ofNullable(利率4_35.get(期数)).map(模型::get剩余本金).orElse(new BigDecimal(0)));
            付出利息 = 付出利息
                    .add(Optional.ofNullable(等额本息模型.get(期数)).map(模型::get利息).orElse(new BigDecimal(0)))
                    .add(Optional.ofNullable(利率4_35.get(期数)).map(模型::get利息).orElse(new BigDecimal(0)));
            结余 = 结余
                    .subtract(Optional.ofNullable(等额本息模型.get(期数)).map(模型::get本息).orElse(new BigDecimal(0)))
                    .subtract(Optional.ofNullable(利率4_35.get(期数)).map(模型::get本息).orElse(new BigDecimal(0)));


            期数++;

        }

        return new 结果(还款本息总额, 剩余待还本金, 付出利息 ,期数-1,结余);


    }

    private static 结果 全部替换(int 限制期数) {
        TreeMap<Integer, 模型> 利率4_35 = 消费贷模型(new BigDecimal(300000), new BigDecimal(每期还款本金), "4.35", 91);
        TreeMap<Integer, 模型> 利率4_70 = 消费贷模型(new BigDecimal(300000), new BigDecimal(每期还款本金), "4.70", 61);
        TreeMap<Integer, 模型> 利率4_75 = 消费贷模型(new BigDecimal(300000), new BigDecimal(每期还款本金), "4.75", 31);
        TreeMap<Integer, 模型> 利率4_75_剩余 = 消费贷模型(new BigDecimal("320301.32"), new BigDecimal(每期还款本金), "4.75", 1);

        int 期数 = 1;

        BigDecimal 还款本息总额 = new BigDecimal(0);
        BigDecimal 剩余待还本金 = new BigDecimal(0);
        BigDecimal 付出利息 = new BigDecimal(0);
        BigDecimal 结余 = new BigDecimal(0);


        while (期数<=限制期数 &&
                (Objects.nonNull(利率4_75_剩余.get(期数))
                        || Objects.nonNull(利率4_75.get(期数))
                        || Objects.nonNull(利率4_70.get(期数))
                        || Objects.nonNull(利率4_35.get(期数))
                )) {

            结余 = 结余.add(new BigDecimal(每期收入));


            还款本息总额 = 还款本息总额
                    .add(Optional.ofNullable(利率4_75.get(期数)).map(模型::get本息).orElse(new BigDecimal(0)))
                    .add(Optional.ofNullable(利率4_35.get(期数)).map(模型::get本息).orElse(new BigDecimal(0)))
                    .add(Optional.ofNullable(利率4_70.get(期数)).map(模型::get本息).orElse(new BigDecimal(0)))
                    .add(Optional.ofNullable(利率4_75_剩余.get(期数)).map(模型::get本息).orElse(new BigDecimal(0)));

            剩余待还本金 = new BigDecimal(0)
                    .add(Optional.ofNullable(利率4_75.get(期数)).map(模型::get剩余本金).orElse(new BigDecimal(0)))
                    .add(Optional.ofNullable(利率4_35.get(期数)).map(模型::get剩余本金).orElse(new BigDecimal(0)))
                    .add(Optional.ofNullable(利率4_70.get(期数)).map(模型::get剩余本金).orElse(new BigDecimal(0)))
                    .add(Optional.ofNullable(利率4_75_剩余.get(期数)).map(模型::get剩余本金).orElse(new BigDecimal(0)));


            付出利息 = 付出利息
                    .add(Optional.ofNullable(利率4_75.get(期数)).map(模型::get利息).orElse(new BigDecimal(0)))
                    .add(Optional.ofNullable(利率4_35.get(期数)).map(模型::get利息).orElse(new BigDecimal(0)))
                    .add(Optional.ofNullable(利率4_70.get(期数)).map(模型::get利息).orElse(new BigDecimal(0)))
                    .add(Optional.ofNullable(利率4_75_剩余.get(期数)).map(模型::get利息).orElse(new BigDecimal(0)));


            结余 = 结余
                    .subtract(Optional.ofNullable(利率4_75.get(期数)).map(模型::get本息).orElse(new BigDecimal(0)))
                    .subtract(Optional.ofNullable(利率4_35.get(期数)).map(模型::get本息).orElse(new BigDecimal(0)))
                    .subtract(Optional.ofNullable(利率4_70.get(期数)).map(模型::get本息).orElse(new BigDecimal(0)))
                    .subtract(Optional.ofNullable(利率4_75_剩余.get(期数)).map(模型::get本息).orElse(new BigDecimal(0)));

            期数++;
        }

        return new 结果(还款本息总额, 剩余待还本金, 付出利息 ,期数-1,结余);


    }


    private static 结果 替换30(int 限制期数) {
        TreeMap<Integer, 模型> 等额本息模型 = 等额本息模型(new BigDecimal(300000));
        TreeMap<Integer, 模型> 利率4_35 = 消费贷模型(new BigDecimal(300000), new BigDecimal(每期还款本金), "4.35", 21);
        TreeMap<Integer, 模型> 利率4_70 = 消费贷模型(new BigDecimal(200000), new BigDecimal(每期还款本金), "4.70", 1);

        int 期数 = 1;

        BigDecimal 还款本息总额 = new BigDecimal(0);
        BigDecimal 剩余待还本金 = new BigDecimal(0);
        BigDecimal 付出利息 = new BigDecimal(0);
        BigDecimal 结余 = new BigDecimal(0);
        while (期数<=限制期数 &&
                (Objects.nonNull(等额本息模型.get(期数))
                        || Objects.nonNull(利率4_35.get(期数))
                        || Objects.nonNull(利率4_70.get(期数))
                )
        ) {
            结余 = 结余.add(new BigDecimal(每期收入));


            还款本息总额 = 还款本息总额
                    .add(Optional.ofNullable(等额本息模型.get(期数)).map(模型::get本息).orElse(new BigDecimal(0)))
                    .add(Optional.ofNullable(利率4_35.get(期数)).map(模型::get本息).orElse(new BigDecimal(0)))
                    .add(Optional.ofNullable(利率4_70.get(期数)).map(模型::get本息).orElse(new BigDecimal(0)));

            剩余待还本金 = new BigDecimal(0)
                    .add(Optional.ofNullable(等额本息模型.get(期数)).map(模型::get剩余本金).orElse(new BigDecimal(0)))
                    .add(Optional.ofNullable(利率4_35.get(期数)).map(模型::get剩余本金).orElse(new BigDecimal(0)))
                    .add(Optional.ofNullable(利率4_70.get(期数)).map(模型::get剩余本金).orElse(new BigDecimal(0)));

            付出利息 = 付出利息
                    .add(Optional.ofNullable(等额本息模型.get(期数)).map(模型::get利息).orElse(new BigDecimal(0)))
                    .add(Optional.ofNullable(利率4_35.get(期数)).map(模型::get利息).orElse(new BigDecimal(0)))
                    .add(Optional.ofNullable(利率4_70.get(期数)).map(模型::get利息).orElse(new BigDecimal(0)));

            结余 = 结余
                    .subtract(Optional.ofNullable(等额本息模型.get(期数)).map(模型::get本息).orElse(new BigDecimal(0)))
                    .subtract(Optional.ofNullable(利率4_35.get(期数)).map(模型::get本息).orElse(new BigDecimal(0)))
                    .subtract(Optional.ofNullable(利率4_70.get(期数)).map(模型::get本息).orElse(new BigDecimal(0)));


            期数++;

        }

        return new 结果(还款本息总额, 剩余待还本金, 付出利息 ,期数-1,结余);



    }
    private static 结果 替换60(int 限制期数) {


        TreeMap<Integer, 模型> 等额本息模型 = 等额本息模型(new BigDecimal(600000));
        TreeMap<Integer, 模型> 利率4_35 = 消费贷模型(new BigDecimal(300000), new BigDecimal(每期还款本金), "4.35", 51);
        TreeMap<Integer, 模型> 利率4_70 = 消费贷模型(new BigDecimal(300000), new BigDecimal(每期还款本金), "4.70", 21);
        TreeMap<Integer, 模型> 利率4_75 = 消费贷模型(new BigDecimal(200000), new BigDecimal(每期还款本金), "4.75", 1);

        int 期数 = 1;

        BigDecimal 还款本息总额 = new BigDecimal(0);
        BigDecimal 剩余待还本金 = new BigDecimal(0);
        BigDecimal 付出利息 = new BigDecimal(0);
        BigDecimal 结余 = new BigDecimal(0);


        while (期数<=限制期数 &&
                (Objects.nonNull(等额本息模型.get(期数))
                        || Objects.nonNull(利率4_35.get(期数))
                        || Objects.nonNull(利率4_70.get(期数))
                        || Objects.nonNull(利率4_75.get(期数))
                )
        ) {
            结余 = 结余.add(new BigDecimal(每期收入));

            还款本息总额 = 还款本息总额
                    .add(Optional.ofNullable(等额本息模型.get(期数)).map(模型::get本息).orElse(new BigDecimal(0)))
                    .add(Optional.ofNullable(利率4_75.get(期数)).map(模型::get本息).orElse(new BigDecimal(0)))
                    .add(Optional.ofNullable(利率4_35.get(期数)).map(模型::get本息).orElse(new BigDecimal(0)))
                    .add(Optional.ofNullable(利率4_70.get(期数)).map(模型::get本息).orElse(new BigDecimal(0)));

            剩余待还本金 = new BigDecimal(0)
                    .add(Optional.ofNullable(等额本息模型.get(期数)).map(模型::get剩余本金).orElse(new BigDecimal(0)))
                    .add(Optional.ofNullable(利率4_75.get(期数)).map(模型::get剩余本金).orElse(new BigDecimal(0)))
                    .add(Optional.ofNullable(利率4_35.get(期数)).map(模型::get剩余本金).orElse(new BigDecimal(0)))
                    .add(Optional.ofNullable(利率4_70.get(期数)).map(模型::get剩余本金).orElse(new BigDecimal(0)));


            付出利息 = 付出利息
                    .add(Optional.ofNullable(等额本息模型.get(期数)).map(模型::get利息).orElse(new BigDecimal(0)))
                    .add(Optional.ofNullable(利率4_75.get(期数)).map(模型::get利息).orElse(new BigDecimal(0)))
                    .add(Optional.ofNullable(利率4_35.get(期数)).map(模型::get利息).orElse(new BigDecimal(0)))
                    .add(Optional.ofNullable(利率4_70.get(期数)).map(模型::get利息).orElse(new BigDecimal(0)));


            结余 = 结余
                    .subtract(Optional.ofNullable(等额本息模型.get(期数)).map(模型::get本息).orElse(new BigDecimal(0)))
                    .subtract(Optional.ofNullable(利率4_75.get(期数)).map(模型::get本息).orElse(new BigDecimal(0)))
                    .subtract(Optional.ofNullable(利率4_35.get(期数)).map(模型::get本息).orElse(new BigDecimal(0)))
                    .subtract(Optional.ofNullable(利率4_70.get(期数)).map(模型::get本息).orElse(new BigDecimal(0)));


            期数++;

        }

        return new 结果(还款本息总额, 剩余待还本金, 付出利息 ,期数-1,结余);


    }
    private static 结果 替换80(int 限制期数) {


        TreeMap<Integer, 模型> 等额本息模型 = 等额本息模型(new BigDecimal(800000));
        TreeMap<Integer, 模型> 利率4_35 = 消费贷模型(new BigDecimal(300000), new BigDecimal(每期还款本金), "4.35", 71);
        TreeMap<Integer, 模型> 利率4_70 = 消费贷模型(new BigDecimal(300000), new BigDecimal(每期还款本金), "4.70", 41);
        TreeMap<Integer, 模型> 利率4_75 = 消费贷模型(new BigDecimal(200000), new BigDecimal(每期还款本金), "4.75", 21);
        TreeMap<Integer, 模型> 利率4_75_陆 = 消费贷模型(new BigDecimal(200000), new BigDecimal(每期还款本金), "4.75", 1);

        int 期数 = 1;

        BigDecimal 还款本息总额 = new BigDecimal(0);
        BigDecimal 剩余待还本金 = new BigDecimal(0);
        BigDecimal 付出利息 = new BigDecimal(0);
        BigDecimal 结余 = new BigDecimal(0);


        while (期数<=限制期数 &&
                (Objects.nonNull(等额本息模型.get(期数))
                        || Objects.nonNull(利率4_35.get(期数))
                        || Objects.nonNull(利率4_70.get(期数))
                        || Objects.nonNull(利率4_75.get(期数))
                        || Objects.nonNull(利率4_75_陆.get(期数))
                )
        ) {
            结余 = 结余.add(new BigDecimal(每期收入));

            还款本息总额 = 还款本息总额
                    .add(Optional.ofNullable(等额本息模型.get(期数)).map(模型::get本息).orElse(new BigDecimal(0)))
                    .add(Optional.ofNullable(利率4_75.get(期数)).map(模型::get本息).orElse(new BigDecimal(0)))
                    .add(Optional.ofNullable(利率4_35.get(期数)).map(模型::get本息).orElse(new BigDecimal(0)))
                    .add(Optional.ofNullable(利率4_70.get(期数)).map(模型::get本息).orElse(new BigDecimal(0)))
                    .add(Optional.ofNullable(利率4_75_陆.get(期数)).map(模型::get本息).orElse(new BigDecimal(0)));

            剩余待还本金 = new BigDecimal(0)
                    .add(Optional.ofNullable(等额本息模型.get(期数)).map(模型::get剩余本金).orElse(new BigDecimal(0)))
                    .add(Optional.ofNullable(利率4_75.get(期数)).map(模型::get剩余本金).orElse(new BigDecimal(0)))
                    .add(Optional.ofNullable(利率4_35.get(期数)).map(模型::get剩余本金).orElse(new BigDecimal(0)))
                    .add(Optional.ofNullable(利率4_70.get(期数)).map(模型::get剩余本金).orElse(new BigDecimal(0)))
                    .add(Optional.ofNullable(利率4_75_陆.get(期数)).map(模型::get剩余本金).orElse(new BigDecimal(0)));


            付出利息 = 付出利息
                    .add(Optional.ofNullable(等额本息模型.get(期数)).map(模型::get利息).orElse(new BigDecimal(0)))
                    .add(Optional.ofNullable(利率4_75.get(期数)).map(模型::get利息).orElse(new BigDecimal(0)))
                    .add(Optional.ofNullable(利率4_35.get(期数)).map(模型::get利息).orElse(new BigDecimal(0)))
                    .add(Optional.ofNullable(利率4_70.get(期数)).map(模型::get利息).orElse(new BigDecimal(0)))
                    .add(Optional.ofNullable(利率4_75_陆.get(期数)).map(模型::get利息).orElse(new BigDecimal(0)));


            结余 = 结余
                    .subtract(Optional.ofNullable(等额本息模型.get(期数)).map(模型::get本息).orElse(new BigDecimal(0)))
                    .subtract(Optional.ofNullable(利率4_75.get(期数)).map(模型::get本息).orElse(new BigDecimal(0)))
                    .subtract(Optional.ofNullable(利率4_35.get(期数)).map(模型::get本息).orElse(new BigDecimal(0)))
                    .subtract(Optional.ofNullable(利率4_70.get(期数)).map(模型::get本息).orElse(new BigDecimal(0)))
                    .subtract(Optional.ofNullable(利率4_75_陆.get(期数)).map(模型::get本息).orElse(new BigDecimal(0)));

            期数++;
        }

        return new 结果(还款本息总额, 剩余待还本金, 付出利息 ,期数-1,结余);
    }

    private static 结果 三十六期之后_470(int 限制期数) {
        TreeMap<Integer, 模型> 利率4_70 = 消费贷模型(new BigDecimal(792000), new BigDecimal(每期还款本金), "4.70", 1);

        int 期数 = 1;

        BigDecimal 还款本息总额 = new BigDecimal(0);
        BigDecimal 剩余待还本金 = new BigDecimal(0);
        BigDecimal 付出利息 = new BigDecimal(0);
        BigDecimal 结余 = new BigDecimal(0);
        while (期数<=限制期数 &&
                (Objects.nonNull(利率4_70.get(期数))
                )
        ) {
            结余 = 结余.add(new BigDecimal(每期收入));


            还款本息总额 = 还款本息总额
                    .add(Optional.ofNullable(利率4_70.get(期数)).map(模型::get本息).orElse(new BigDecimal(0)));

            剩余待还本金 = new BigDecimal(0)
                    .add(Optional.ofNullable(利率4_70.get(期数)).map(模型::get剩余本金).orElse(new BigDecimal(0)));

            付出利息 = 付出利息
                    .add(Optional.ofNullable(利率4_70.get(期数)).map(模型::get利息).orElse(new BigDecimal(0)));

            结余 = 结余
                    .subtract(Optional.ofNullable(利率4_70.get(期数)).map(模型::get本息).orElse(new BigDecimal(0)));


            期数++;

        }

        return new 结果(还款本息总额, 剩余待还本金, 付出利息 ,期数-1,结余);



    }


    private static 结果 全_470(int 限制期数) {
        TreeMap<Integer, 模型> 利率4_70 = 消费贷模型(new BigDecimal(1200000), new BigDecimal(每期还款本金), "4.65", 1);

        int 期数 = 1;

        BigDecimal 还款本息总额 = new BigDecimal(0);
        BigDecimal 剩余待还本金 = new BigDecimal(0);
        BigDecimal 付出利息 = new BigDecimal(0);
        BigDecimal 结余 = new BigDecimal(0);
        while (期数<=限制期数 &&
                (Objects.nonNull(利率4_70.get(期数))
                )
        ) {
            结余 = 结余.add(new BigDecimal(每期收入));


            还款本息总额 = 还款本息总额
                    .add(Optional.ofNullable(利率4_70.get(期数)).map(模型::get本息).orElse(new BigDecimal(0)));

            剩余待还本金 = new BigDecimal(0)
                    .add(Optional.ofNullable(利率4_70.get(期数)).map(模型::get剩余本金).orElse(new BigDecimal(0)));

            付出利息 = 付出利息
                    .add(Optional.ofNullable(利率4_70.get(期数)).map(模型::get利息).orElse(new BigDecimal(0)));

            结余 = 结余
                    .subtract(Optional.ofNullable(利率4_70.get(期数)).map(模型::get本息).orElse(new BigDecimal(0)));


            期数++;

        }

        return new 结果(还款本息总额, 剩余待还本金, 付出利息 ,期数-1,结余);



    }



    public static void main(String[] args) {
        int 限制期数 = 36;
        结果 方案_不换房贷 = 方案_不换房贷(限制期数);
        结果 全部替换 = 全部替换(限制期数);
        结果 替换30 = 替换30(限制期数);
        结果 替换60 = 替换60(限制期数);
        结果 替换80 = 替换80(限制期数);


//        System.out.println(全部替换);
//        System.out.println(替换80);
//        System.out.println(替换60);
//        System.out.println(替换30);
//        System.out.println(方案_不换房贷);

//        等额本息模型(new BigDecimal(0));
//        TreeMap<Integer, 模型> 利率4_35 = 消费贷模型(new BigDecimal(200000), new BigDecimal(10000), "4.35", 10);

//        System.out.println(三十六期之后_470(36));
//        System.out.println(全_470(60));
    }


}
