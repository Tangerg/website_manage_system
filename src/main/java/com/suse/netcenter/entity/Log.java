package com.suse.netcenter.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author Tangerg
 * @create 2019-03-25 20:09
 */
@Data
@TableName(value = "log")
public class Log {

    @TableId(value = "log_id")
    private Integer logId;

    @TableField(value = "log_job_num")
    private Integer logJobNum;

    @TableField(value = "log_ip")
    private Integer logIp;

    @TableField(value = "log_operate")
    private Integer logOperate;

    @TableField(value = "log_additional")
    private Integer logAdd;

    @TableField(value = "log_create_time")
    private Date logCreateTime;
}
