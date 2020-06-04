package com.offcn.reptile.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.offcn.reptile.domain.New;
import com.offcn.reptile.mappers.NewMapper;
import com.offcn.reptile.service.NewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: Reptile
 * @description:
 * @author: 刘宇航
 * @create: 2020-06-01 22:36
 **/
@Service
@Transactional
@Slf4j
public class NewServiceImpl implements NewService {

    @Autowired
    private NewMapper mapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void addNew(List<New> newList) {

        newList.forEach(e -> mapper.insert(e));

    }

    @Override
    public void addNew(New anew) {
        mapper.insert(anew);
    }

    @Override
    @Scheduled(cron = "0 */20 * * * ?")
    public void addNewForRedis() {
        //从redis里获取数据
        List<String> news = redisTemplate.opsForHash().values("NEWS");

        //将json转换为对象
        List<New> newList = news.stream().map(e -> JSONObject.parseObject(e, New.class)).collect(Collectors.toList());

        //将对象集存入数据库
        newList.stream().filter(e -> mapper.selectByPrimaryKey(e.getId()) == null).forEach(e -> mapper.insert(e));

    }

}
