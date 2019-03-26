package com.suse.netcenter.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Tangerg
 * @create 2019-03-25 20:08
 */
@Data
@TableName(value = "user")
public class User {
    @TableId(value = "user_id")
    private Integer userId;

    @TableField(value = "user_job_num")
    private Integer userJobNum;

    @TableField(value = "user_name")
    private String userName;

    @TableField(value = "user_password")
    private String userPassword;

    @TableField(value = "user_gender")
    private Integer userGender;

    @TableField(value = "user_department_id")
    private Integer userDeptId;

    @TableField(value = "user_office_phone")
    private String userOfficePhone;

    @TableField(value = "user_tel")
    private String userTel;

    @TableField(value = "user_is_quit")
    private Integer userIsQuit;

    @TableField(value = "user_roles")
    private Integer userRoles;
}
