package com.suse.netcenter.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author Tangerg
 * @create 2019-03-25 20:09
 */
@Data
@TableName(value = "department")
public class Department {

    @TableId(value = "department_id")
    private Integer deptId;

    @NotBlank(message = "部门名称不能为空")
    @TableField(value = "department_name")
    private String deptName;

    @NotNull(message = "部门负责人工号不能为空")
    @Pattern(regexp = "^\\d{5}$", message = "工号格式不正确")
    @TableField(value = "department_director")
    private String deptDirector;

    @TableField(exist = false)
    private String deptDirectorName;

    @NotBlank(message = "部门办公电话不能为空")
    @Pattern(regexp = "[0-9-()()]{7,18}", message = "电话格式不正确")
    @TableField(value = "department_office_phone")
    private String deptOfficePhone;

    @TableField(exist = false)
    private Integer deptTotalPeople;

    @TableField(exist = false)
    private Integer deptTotalWebSite;
}
