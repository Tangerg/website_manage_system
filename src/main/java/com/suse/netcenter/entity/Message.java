package com.suse.netcenter.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author Tangerg
 * @create 2019-03-25 20:09
 */
@Data
public class Message {
    private Integer mesId;
    private Integer mesSender;
    private Integer mesReceiver;
    private Integer mesType;
    private Integer mesApplication;
    private String mesAdd;
    private Integer mesState;
    private Integer mesIsDel;
    private Date mesCreateTime;
}
