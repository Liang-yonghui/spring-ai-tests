package com.rhine.springaitests;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.rhine")
public class SpringAiTestsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAiTestsApplication.class, args);
    }

}
