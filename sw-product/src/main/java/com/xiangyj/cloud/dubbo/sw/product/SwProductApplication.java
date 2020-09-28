package com.xiangyj.cloud.dubbo.sw.product;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class SwProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(SwProductApplication.class, args);
    }
}
