package com.offcn.reptile.util;

import com.alibaba.fastjson.JSON;
import com.offcn.reptile.enums.ResultState;
import com.offcn.reptile.vo.ResultVo;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * @program: Reptile
 * @description:
 * @author: 刘宇航
 * @create: 2020-05-28 16:41
 **/
@Component
public class DataUtil {
    /**
     * 用于讲普通的json数据解析为map
     * @param url
     * @param restTemplate
     * @return
     */
    public static List<Map> getListMapData(String url, RestTemplate restTemplate){
        //获得结果信息
        ResultVo resp = restTemplate.getForObject(url, ResultVo.class);

        if (resp != null && resp.getCode() == ResultState.SUCCESS.getCode()) {
            //将数据转换为数组
            List<Map> maps = JSON.parseArray(JSON.toJSON(resp.getData()).toString(), Map.class);
            //如果转换不为空，则返回
            if (maps != null && maps.size() > 0) return maps;

        }
        return null;
    }
}
