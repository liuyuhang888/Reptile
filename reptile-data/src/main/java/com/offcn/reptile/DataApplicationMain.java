package com.offcn.reptile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @program: Reptile
 * @description:
 * @author: 刘宇航
 * @create: 2020-05-27 15:27
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
public class DataApplicationMain {
    public static void main(String[] args) {
        SpringApplication.run(DataApplicationMain.class);
    }
}
