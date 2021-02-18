package com.enzo.demo;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CyclicBarrierDemo {



    public static void main(String[] args) throws Exception{
        int threadNum = 5;


        final int[] count = {0};

        Runnable t = () -> System.out.println("线程名称:" + Thread.currentThread().getName() +"执行完毕,count值为：" + count[0]);

        CyclicBarrier cyclicBarrier = new CyclicBarrier(threadNum, t);


        while (threadNum >0){
            Thread thread = new Thread(() -> {
                try {


                    for (int i=0;i<100;i++){
                        count[0]++;
                    }
//                    Random random = new Random();
//                    int sl = random.nextInt(5);

//                    Thread.sleep(sl*1000);

                    cyclicBarrier.await(3000, TimeUnit.MILLISECONDS);// genaration 1



                    for (int i=0;i<100;i++){
                        count[0]++;
                    }
                    cyclicBarrier.await();

                }catch (BrokenBarrierException e){
                    System.out.println(Thread.currentThread().getName() +"冲破");
                }
                catch (InterruptedException e){
                    System.out.println(Thread.currentThread().getName() +"中断");
                }catch (TimeoutException e){
                    System.out.println(Thread.currentThread().getName() +"超时");
                }
            });



            thread.start();

            threadNum--;

        }


        Thread.sleep(5000);

        System.out.println(count[0]);



    }
}
