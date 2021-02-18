package com.enzo.spring.bean;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class SimpleBean implements InitializingBean {




    public void initSayHello(){

        System.out.println("hello enzo");
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                while (true){
                    char[] a = new char[1024*1024];


                }

            }
        });

        thread.start();
    }
}
