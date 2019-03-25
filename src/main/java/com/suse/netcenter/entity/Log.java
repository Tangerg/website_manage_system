package com.suse.netcenter.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author Tangerg
 * @create 2019-03-25 20:09
 */
@Data
public class Log {
    private Integer logId;
    private Integer logJobNum;
    private Integer logIp;
    private Integer logOperate;
    private Integer logAdd;
    private Date logCreateTime;
}
