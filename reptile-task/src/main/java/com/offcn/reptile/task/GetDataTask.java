package com.offcn.reptile.task;

import com.offcn.reptile.service.GetDataTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @program: Reptile
 * @description:
 * @author: 刘宇航
 * @create: 2020-05-29 10:53
 **/
@Component
@Slf4j
public class GetDataTask {

    @Autowired
    private GetDataTaskService getDataTaskService;

    @Scheduled(cron = "0 */5 * * * ?")
    public void getData() throws InterruptedException {
        getDataTaskService.getData();
    }
    public void getData_TimeOut() {
        log.info("爬虫失败，已经降级");
    }
}
