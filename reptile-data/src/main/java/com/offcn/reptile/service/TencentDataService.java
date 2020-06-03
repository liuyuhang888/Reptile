package com.offcn.reptile.service;

import com.offcn.reptile.domain.New;

import java.io.IOException;
import java.util.List;

/**
 * @program: Reptile
 * @description:
 * @author: 刘宇航
 * @create: 2020-05-27 15:42
 **/
public interface TencentDataService {
    /**
     * 获取下载和拼装好的数据
     * @param url 爬取的路径
     * @param filepath 爬取图片的下载路径
     * @return 返回拼装好的数据
     */
    List<New> getData(String url, String filepath);

    /**
     * 服务熔断方法
     * @param url
     * @param filepath
     * @return
     */
    List<New> getData_TimeOut (String url, String filepath);

    /**
     * 获取未下载的数据，此时图片地址引用的为源路径
     * @param url 爬取的路径
     * @return 返回键拼装好的原始数据
     */
    List<New> getTencentOrigenalData(String url);

    /**
     * 将图片改为本地并下载
     * @param newList 原始的数据集合
     * @param filepath 下载的路径
     * @return 返回拼装并下载好的原始数据
     */
    List<New> tencentDatadownLoadImage(List<New> newList, String filepath) throws IOException;
}
