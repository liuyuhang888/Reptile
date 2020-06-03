package com.offcn.reptile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @program: Reptile
 * @description:
 * @author: 刘宇航
 * @create: 2020-05-29 10:34
 **/
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@EnableScheduling
public class TaskApplicationMain {
    public static void main(String[] args) {
        SpringApplication.run(TaskApplicationMain.class);
    }
}

 