package com.offcn.reptile.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: Reptile
 * @description:
 * @author: 刘宇航
 * @create: 2020-05-29 17:57
 **/
@Configuration
public class feignConfig {
    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}
