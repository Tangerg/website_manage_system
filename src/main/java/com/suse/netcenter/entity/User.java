package com.suse.netcenter.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author Tangerg
 * @create 2019-03-25 20:08
 */
@Data
@TableName(value = "user")
public class User {
    @TableId(value = "user_id")
    private Integer userId;


    @NotNull(message = "工号不能为空")
    @Pattern(regexp = "^\\d{5}$", message = "工号格式不正确")
    @TableField(value = "user_job_num")
    private String userJobNum;


    @NotBlank(message = "用户名不能为空")
    @TableField(value = "user_name")
    private String userName;

    @JsonIgnore
    @TableField(value = "user_password")
    private String userPassword;

    @TableField(value = "user_gender")
    private Integer userGender;

    @TableField(value = "user_department_id")
    private Integer userDeptId;

    @TableField(value = "user_office_phone")
    @Pattern(regexp = "[0-9-()()]{7,18}", message = "电话格式不正确")
    private String userOfficePhone;

    @Pattern(regexp = "0?(13|14|15|17|18)[0-9]{9}", message = "手机号格式不正确")
    @TableField(value = "user_tel")
    private String userTel;

    @JsonIgnore
    @TableField(value = "user_is_quit")
    private Integer userIsQuit;

    @TableField(value = "user_roles")
    private Integer userRoles;

    @TableField(exist = false)
    private String userDeptName;
}
