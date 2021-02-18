package com.enzo.lock;


import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 锁相关的学习记录
 */
public class LockMain{


    final static Object obj1 = new Object();
    final static Object obj2 = new Object();

//    public static void main(String[] args) {
//        LockMain test = new LockMain();
//        Thread t1 = new Thread(test.new DeadThread1());
//        Thread t2 = new Thread(test.new DeadThread2());
//
//        t1.start();
//        t2.start();
//    }


    class DeadThread1 implements Runnable {

        @Override
        public void run() {
            synchronized(obj1){

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" lock obj1");
                synchronized(obj2){
                    System.out.println(Thread.currentThread().getName()+"is running");
                }

            }
        }

    }

    class DeadThread2 implements Runnable {

        @Override
        public void run() {
            synchronized (obj2) {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " lock obj2");
                synchronized (obj1) {
                    System.out.println(Thread.currentThread().getName() + "is running");
                }

            }
        }


    }


//    public static void main(String[] args) {
//
//
//        final Thread sleepThread = new Thread() {
//
//            @Override
//            public void run() {
//
//                try {
//                    System.out.println("sleepThread now is begin to sleep");
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//
//                }
//
//                super.run();
//
//            }
//
//        };
//
//
//        final Thread busyThread = new Thread(() -> {
//            while (true) {
//                System.out.println("busyThread is running");
//                if (Thread.currentThread().isInterrupted()) {
//                    System.out.println("busyThread is interrupted , log now");
//                    break;
//                }
//            }
//        });
//
//
//        sleepThread.start();
//        busyThread.start();
//
//        System.out.println("before interrupt");
//        sleepThread.interrupt();
//        busyThread.interrupt();
//
//
//
//        System.out.println("sleepThread is : " + sleepThread.isInterrupted());
//        System.out.println("busyThread is : " + busyThread.isInterrupted());
//    }


//    private static AtomicInteger count=new AtomicInteger(0);
//    public static void main(String[] args) throws InterruptedException {
//
//        for (int i=0;i<10;i++){
//            Thread thread = new Thread(()-> {
//
//                for (int j = 0;j<1000;j++){
//                    count.addAndGet(1);
//                }
//
//                System.out.println(Thread.currentThread().getName() + " calculate count is:" + count);
//            });
//
//            thread.start();
//        }
//
//
//
//
//        System.out.println("main Thread count is :"+ count);
//
//    }

//
//    private static ReentrantLock lock = new ReentrantLock();
//
//    public static void main(String[] args) {
//        for (int i = 0; i < 5; i++) {
//            Thread thread = new Thread(() -> {
//                lock.lock();
//                try {
//                    Thread.sleep(10000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } finally {
//                    lock.unlock();
//                }
//            });
//            thread.start();
//        }
//    }


}
