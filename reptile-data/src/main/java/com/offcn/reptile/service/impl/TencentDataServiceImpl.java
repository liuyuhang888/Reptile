package com.offcn.reptile.service.impl;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.offcn.reptile.domain.New;
import com.offcn.reptile.service.TencentDataService;
import com.offcn.reptile.util.DataUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @program: Reptile
 * @description:
 * @author: 刘宇航
 * @create: 2020-05-27 15:42
 **/
@Service
@Slf4j
public class TencentDataServiceImpl implements TencentDataService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 封装数据接口为外界提供快捷调用
     *
     * @param url      爬取的路径
     * @param filepath 爬取图片的下载路径
     * @return 返回拼装好的数据
     */
    @HystrixCommand(fallbackMethod = "getData_TimeOut", commandProperties = {
            //超时服务降级
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            //单位窗口期的请求次数最大为
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            //窗口间隔期
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "1000"),
            //失败率多少跳闸
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),
    }
    )
    public List<New> getData(String url, String filepath) {
        //获取源数据
        List<New> origenalData = getTencentOrigenalData(url);
        List<New> news = null;
        try {
            //下载图片并替换图片地址
            news  = tencentDatadownLoadImage(origenalData, filepath);
        }catch (Exception e){
            e.printStackTrace();
            log.info("下载图片失败");
        }
        return news;
    }
    public List<New> getData_TimeOut (String url, String filepath){
        log.info("获取数据失败");
        return null;
    }

    @Override
    public List<New> getTencentOrigenalData(String url) {
        //获取map形式的数据集
        List<Map> dataList = DataUtil.getListMapData(url, restTemplate);
        //创建返回的源数据集合
        List<New> result = new ArrayList<>();
        //转换为自定义entity
        assert dataList != null;

        for (int i = 0; i < dataList.size(); i++) {
            //创建用于存放图片集的集合
            List<String> imgs = new ArrayList<>();
            HashOperations ops = redisTemplate.opsForHash();
            //判断redis中是否存在该新闻，如果存在就不处理
            if (ops.hasKey("NEWS", dataList.get(i).get("id"))) {
                continue;
            }
            New anew = new New(dataList.get(i).get("id").toString(), dataList.get(i).get("category_chn").toString(), dataList.get(i).get("title").toString(), dataList.get(i).get("intro").toString(), null, null);
            try {
                //将这篇文字的所有文字拼在一起
                StringBuilder sb = new StringBuilder();
                Document document = Jsoup.connect(dataList.get(i).get("vurl").toString()).get();
                document.select(".content-article p").forEach(doc -> sb.append(doc.toString()));
                document.select(".content-article img").forEach(img -> imgs.add(img.attr("src")));
                //获取图片地址
                anew.setImgs(imgs);
                //拼接完成，组装对象
                anew.setContent(sb.toString());
                //添加到新闻集合
                result.add(anew);
                log.info("拼装{}条新闻:{}", i, anew);

            } catch (IOException ioException) {
                ioException.printStackTrace();
                log.info("下载数据异常，请检查路径");
            }
        }
        return result;
    }

    public List<New> tencentDatadownLoadImage(List<New> newList, String filepath) throws IOException {
        //读写分离
        List<New> result = new ArrayList<>(45);
        result.addAll(newList);

        for (int i = 0; i < newList.size(); i++) {
            for (int j = 0; j < newList.get(i).getImgs().size(); j++) {
                String imgUrl = newList.get(i).getImgs().get(j);
                log.info("将要下载http:{}",imgUrl);
                String newsrc = downLoudByfile(imgUrl, filepath);
                //将旧src换成新的
                result.get(i).getImgs().set(j,newsrc);
            }
            redisTemplate.opsForHash().put("NEWS", newList.get(i).getId(), JSON.toJSONString(result.get(i)));
        }
        return result;
    }

    //下载到本地
    private String downLoudByfile(String imgUrl, String filepath) throws IOException {
        //自定义的请求头信息
        HttpHeaders headers = new HttpHeaders();
        //封装请求头和请求参数
        HttpEntity httpEntity = new HttpEntity<>(headers);
        //获取图片相应信息
        ResponseEntity<byte[]> exchange = restTemplate.exchange("http:" + imgUrl, HttpMethod.GET, httpEntity, byte[].class);
        //拼装图片路径
        String fileName = IdUtil.simpleUUID() + "." + exchange.getHeaders().getContentType().getSubtype();
        String filesrc = filepath+fileName;
        //写出
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filesrc));
        bos.write(Objects.requireNonNull(exchange.getBody()));
        bos.flush();
        bos.close();
        log.info("已下载图片：{},存放地址：{}",fileName,filesrc);
        return filesrc;
    }

    //下载到文件服务器
    private void downLoudByServer(ResponseEntity<byte[]> exchange, File file, String url) throws IOException {
        //TODO
    }


}
