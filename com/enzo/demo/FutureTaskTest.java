package com.enzo.demo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class FutureTaskTest {


//    public static void main(String[] args) {
//
//        Thread.currentThread().setName("主线程-main");
//
//
//        Callable<Integer> callable = () -> {
////            Thread.sleep(1000* 60);
////            Thread.currentThread().setName("callable线程");
//            System.out.println(Thread.currentThread().getName() + "开始运行");
//            int i = 0;
//            while (i<5){
//                Thread.sleep(1000 * i);
//                i++;
//                System.out.println(Thread.currentThread().getName() + "运行中，当前第" + i + "次循环");
//            }
////            System.out.println(Thread.currentThread().getName() + "正在运行");
//            return 1;
//        };
//
//
//        FutureTask<Integer> futureTask = new FutureTask<>(callable);
//
//        ExecutorService  executorService = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new  LinkedBlockingQueue<>());
//        executorService.execute(futureTask);
//
//        //这种直接run的，是在主线程里执行，并不会提现异步执行的效果
////        futureTask.run();
//
////        System.out.println(Thread.currentThread().getName() +"正在运行");
//        try {
//
//            while (true){
//                System.out.println("尝试获取结果");
//                Integer i = futureTask.get();
//                System.out.println("运行结果:"+i);
//
//
//                if (i != null){
//                    break;
//                }
//            }
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println();
//
//
//
//
//
//    }

//
//    public static void main(String[] args) {
//
//        LockSupport.unpark(Thread.currentThread());
//        System.out.println(11111);
//        LockSupport.park();
//        System.out.println(22222);
//
//    }










}
