package com.offcn.reptile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @program: Reptile
 * @description:
 * @author: 刘宇航
 * @create: 2020-05-29 23:45
 **/
@SpringBootApplication
@EnableHystrixDashboard
public class DashBoardApplicationMain {
    public static void main(String[] args) {
        SpringApplication.run(DashBoardApplicationMain.class);
    }
}
