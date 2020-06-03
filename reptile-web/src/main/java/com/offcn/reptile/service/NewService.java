package com.offcn.reptile.service;

import com.offcn.reptile.domain.New;

import java.util.List;

/**
 * @program: Reptile
 * @description:
 * @author: 刘宇航
 * @create: 2020-06-01 22:36
 **/
public interface NewService {

    /**
     * 批量添加新闻
     * @param newList
     */
    void addNew(List<New> newList);

    /**
     * 添加单个新闻
     * @param anew
     */
    void addNew(New anew);

    void addNewForRedis();

}
