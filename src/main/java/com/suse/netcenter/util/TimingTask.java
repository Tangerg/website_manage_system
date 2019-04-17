package com.suse.netcenter.util;

import com.suse.netcenter.service.impl.InformationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Tangerg
 * @create 2019-04-08 10:33
 * 定时任务:每日定时更新统计表
 */
@Component
@Configuration
public class TimingTask {

    @Autowired
    private InformationImpl informationImpl;

    //每隔1个小时更新一次最后一条记录
    @Scheduled(cron = "0 0 */1 * * ?")
    //@Scheduled(cron = "*/10 * * * * ?")
    public void timingUpdateRecords() throws InterruptedException {
        informationImpl.updateRecord();
    }

    //每天01点01分插入一条新的记录
    @Scheduled(cron = "0 1 1 ? * *")
    //@Scheduled(cron = "*/10 * * * * ?")
    public void timingInsertRecords() throws InterruptedException {
        informationImpl.addNewRecord();
    }

}
