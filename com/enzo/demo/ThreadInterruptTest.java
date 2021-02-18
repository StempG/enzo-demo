package com.enzo.demo;

public class ThreadInterruptTest extends Thread{

    public void run(){
        while(true){
            if(Thread.currentThread().isInterrupted()){
                System.out.println("Someone interrupted me.");
                break;
            }
            else{
                System.out.println("Thread is Going...");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadInterruptTest t = new ThreadInterruptTest();
        t.start();
        Thread.sleep(3000);
        t.interrupt();
    }


}
