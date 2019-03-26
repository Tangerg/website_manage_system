package com.suse.netcenter.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Tangerg
 * @create 2019-03-25 20:09
 */
@Data
@TableName(value = "department")
public class Department {

    @TableId(value = "department_id")
    private Integer deptId;

    @TableField(value = "department_name")
    private String deptName;

    @TableField(value = "department_director")
    private Integer deptDirector;

    @TableField(value = "department_office_phone")
    private String deptOfficePhone;

    @TableField(value = "department_total_people")
    private Integer deptTotalPeople;

    @TableField(value = "department_total_website")
    private Integer deptTotalWebSite;
}
