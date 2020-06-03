package com.offcn.reptile.service.impl;

import com.offcn.reptile.DataApplicationMain;
import com.offcn.reptile.domain.New;
import com.offcn.reptile.service.TencentDataService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @program: Reptile
 * @description:
 * @author: 刘宇航
 * @create: 2020-05-27 16:29
 **/
@SpringBootTest(classes = DataApplicationMain.class)
@Slf4j
class TencentDataServiceImplTest {

    @Autowired
    private TencentDataService service;

    @Test
    void getData() {
        for (int i =1; i<5; i++){
        String url = "https://pacaio.match.qq.com/irs/rcd?cid=146&token=49cbb2154853ef1a74ff4e53723372ce&ext=ent&page="+i;
        List<New> data = service.getData(url, "E:\\img\\");
        data.forEach(System.out::println);
        }
    }
}