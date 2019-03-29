package com.suse.netcenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@MapperScan("com.suse.netcenter.mapper")
@SpringBootApplication
public class NetcenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(NetcenterApplication.class, args);
    }

}
