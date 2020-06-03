package com.offcn.reptile.service;

import com.offcn.reptile.vo.QueryVo;

import java.util.List;

/**
 * @program: Reptile
 * @description:
 * @author: 刘宇航
 * @create: 2020-06-03 00:01
 **/
public interface SearchService {

    List<QueryVo> search(String keywords,int pageNumber,int pageSize);

}
