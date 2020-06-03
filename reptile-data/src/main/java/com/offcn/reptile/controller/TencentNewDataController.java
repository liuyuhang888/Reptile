package com.offcn.reptile.controller;

import com.offcn.reptile.domain.New;
import com.offcn.reptile.service.TencentDataService;
import com.offcn.reptile.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * @program: Reptile
 * @description:
 * @author: 刘宇航
 * @create: 2020-05-27 15:39
 **/

@RestController
@RequestMapping("/tencent")
public class TencentNewDataController {
    @Autowired
    private TencentDataService service;

    @GetMapping("/data")
    public ResultVo getdata() {
        for (int i = 1; i < 5; i++) {
            String url = "https://pacaio.match.qq.com/irs/rcd?cid=146&token=49cbb2154853ef1a74ff4e53723372ce&ext=ent&page=" + i;
            List<New> data = service.getData(url, "E:\\img\\");
            if (Objects.isNull(data)) {
                return new ResultVo("爬虫失败", 500);
            }
        }
        return new ResultVo("爬虫已开启", 200);
    }
}
