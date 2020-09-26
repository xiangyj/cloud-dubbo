package com.xiangyj.cloud.dubbo.zipkin.order;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class ZipkinOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZipkinOrderApplication.class, args);
    }
}
