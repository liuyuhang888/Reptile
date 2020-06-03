package com.offcn.reptile.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @program: Reptile
 * @description:
 * @author: 刘宇航
 * @create: 2020-05-29 10:50
 **/
@Service
@FeignClient(value = "REPTILE-DATA")
public interface GetDataTaskService {
    @GetMapping("/tencent/data")
     String getData();
}
