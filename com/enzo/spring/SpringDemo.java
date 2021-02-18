package com.enzo.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringDemo {


    public static void main(String[] args) {


        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext ("classpath:spring/applicationContext.xml");
        applicationContext.start();
        while (true){

        }
//        System.out.println("spring context started");
    }
}
