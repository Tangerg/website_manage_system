package com.suse.netcenter.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.Pattern;
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

    @Pattern(regexp = "^\\d{5}$", message = "工号格式不正确")
    @TableField(value = "message_sender")
    private String msgSender;

    @TableField(exist = false)
    private String msgSenderName;

    @Pattern(regexp = "^\\d{5}$", message = "工号格式不正确")
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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "message_create_time")
    private Date msgCreateTime;
}
