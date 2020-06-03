package com.offcn.reptile.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: Reptile
 * @description:
 * @author: 刘宇航
 * @create: 2020-06-03 21:42
 **/
@Configuration
public class webConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("/search");
    }
}
