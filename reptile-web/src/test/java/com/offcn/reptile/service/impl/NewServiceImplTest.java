package com.offcn.reptile.service.impl;

import com.offcn.reptile.service.NewService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @program: Reptile
 * @description:
 * @author: 刘宇航
 * @create: 2020-06-01 22:47
 **/
@SpringBootTest
class NewServiceImplTest {

    @Autowired
    private NewService service;

    @Test
    void addNew() {
//        New anew = new New();
//        anew.setId("1212341");
//        anew.setIntro("测试数据");
//        service.addNew(anew);
        service.addNewForRedis();
    }
}