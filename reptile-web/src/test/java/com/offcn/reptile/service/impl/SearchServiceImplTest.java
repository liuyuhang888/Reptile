package com.offcn.reptile.service.impl;

import com.offcn.reptile.service.SearchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @program: Reptile
 * @description:
 * @author: 刘宇航
 * @create: 2020-06-03 15:20
 **/
@SpringBootTest
class SearchServiceImplTest {

    @Autowired
    private SearchService searchService;

    @Test
    void search() {
        //List<QueryVo> queryVoList = searchService.search("袁姗姗", 0, 10);
        //queryVoList.forEach(System.out::println);
    }
}