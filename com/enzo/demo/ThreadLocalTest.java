package com.enzo.demo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ThreadLocalTest {



    private static final ThreadLocal<Integer> threadLocalA= ThreadLocal.withInitial(() -> 100);

    private static final ThreadLocal<Integer> threadLocalB= ThreadLocal.withInitial(() -> 200);


//    public static void main(String[] args) {
//
//
//        System.out.println(threadLocalA.get());
//        threadLocalA.set(999);
//        System.out.println(threadLocalA.get());
//
//
//    }


}
