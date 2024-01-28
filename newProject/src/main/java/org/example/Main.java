package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController("/")
@ComponentScan(basePackages = "org.example")
@SpringBootApplication
public class Main {
    public static void main(String[] args) {

       // System.out.println("Hello world!");

        SpringApplication.run(Main.class,args);
    }

//    @GetMapping("test")
//    public static String test(){
//        return "Test Passed";
//    }
}