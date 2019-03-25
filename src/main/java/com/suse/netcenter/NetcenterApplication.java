package com.suse.netcenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement  // 启注解事务管理，等同于xml配置方式的 <tx:annotation-driven />
@MapperScan("com.suse.netcenter.mapper") //扫描mapper包位置
@SpringBootApplication
public class NetcenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(NetcenterApplication.class, args);
    }

}
