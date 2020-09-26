package com.xiangyj.cloud.dubbo.zipkin.product;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class ZipkinProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZipkinProductApplication.class, args);
    }
}
