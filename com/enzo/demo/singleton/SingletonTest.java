package com.enzo.demo.singleton;

public class SingletonTest {




    private static final SingletonTest singleton = new SingletonTest();


    private SingletonTest(){

        synchronized (SingletonTest.class){
            if (singleton != null){
                throw new RuntimeException("already has one instance");
            }
        }
    }



    public static SingletonTest getInstance(){
        return singleton;
    }
}


class Test{


    public static void main(String[] args) {
        SingletonTest instance =  SingletonTest.getInstance();

        System.out.println(instance);

    }
}
