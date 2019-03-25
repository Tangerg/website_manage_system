package com.suse.netcenter.entity;

import lombok.Data;

/**
 * @author Tangerg
 * @create 2019-03-25 20:09
 */
@Data
public class Department {
    private Integer deptId;
    private String deptName;
    private Integer deptDirector;
    private String deptOfficePhone;
    private Integer deptTotalPeople;
    private Integer deptTotalWebSite;
}
