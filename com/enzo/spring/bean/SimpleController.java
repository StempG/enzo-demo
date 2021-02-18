package com.enzo.spring.bean;


import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/enzo/demo")
public class SimpleController {



    @Bean
    public Object s(){
        System.out.println(1231);
        return "111";
    }


    @RequestMapping(value = "/hello")
    public String hello(){
        return "hello ";
    }
}
