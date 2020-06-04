package com.offcn.reptile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @program: Reptile
 * @description:
 * @author: 刘宇航
 * @create: 2020-05-29 22:15
 **/
@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = "com.offcn.reptile.mappers")
@EnableScheduling
public class WebApplicationMain {
    public static void main(String[] args) {
        SpringApplication.run(WebApplicationMain.class);
    }
}
