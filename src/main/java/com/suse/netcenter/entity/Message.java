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
@TableName(value = "message")
public class Message {
    @TableId(value = "message_id")
    private Integer mesId;

    @TableField(value = "message_sender")
    private Integer mesSender;

    @TableField(value = "message_receiver")
    private Integer mesReceiver;

    @TableField(value = "message_type")
    private Integer mesType;

    @TableField(value = "message_application")
    private Integer mesApplication;

    @TableField(value = "message_additional")
    private String mesAdd;

    @TableField(value = "message_state")
    private Integer mesState;

    @TableField(value = "message_is_del")
    private Integer mesIsDel;

    @TableField(value = "message_create_time")
    private Date mesCreateTime;
}
