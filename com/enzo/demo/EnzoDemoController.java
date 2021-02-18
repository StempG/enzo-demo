package com.enzo.demo;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/enzo")
public class EnzoDemoController {



    @RequestMapping("/test/hello")
    public String testHello(){

        return "hello enzo";
    }








}
