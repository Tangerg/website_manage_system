package com.suse.netcenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableScheduling//开启定时任务功能
@EnableTransactionManagement
@MapperScan("com.suse.netcenter.mapper")
@SpringBootApplication
public class NetcenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(NetcenterApplication.class, args);
    }

}
