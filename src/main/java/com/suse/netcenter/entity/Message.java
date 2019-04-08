package com.suse.netcenter.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Integer msgId;

    @TableField(value = "message_sender")
    private String msgSender;

    @TableField(value = "message_receiver")
    private String msgReceiver;

    @TableField(value = "message_type")
    private Integer msgType;

    @TableField(value = "message_application")
    private Integer msgApplication;

    @TableField(value = "message_additional")
    private String msgAdd;

    @TableField(value = "message_state")
    private Integer msgState;

    @JsonIgnore
    @TableField(value = "message_is_del")
    private Integer msgIsDel;

    @TableField(value = "message_create_time")
    private Date msgCreateTime;
}
