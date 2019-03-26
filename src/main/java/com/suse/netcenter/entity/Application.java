package com.suse.netcenter.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author Tangerg
 * @create 2019-03-25 20:08
 */
@Data
@TableName(value = "application")
public class Application {

    @TableId(value = "application_id")
    private Integer appId;

    @TableField(value = "application_department_id")
    private Integer appDeptId;

    @TableField(value = "application_record_operate")
    private Integer appRecOperate;

    @TableField(value = "application_department_director")
    private Integer appDeptDirector;

    @TableField(value = "application_office_phone")
    private String appOfficePhone;

    @TableField(value = "application_website_director_num")
    private Integer appWebDirectorNum;

    @TableField(value = "application_website_director_name")
    private String appWebDirectorName;

    @TableField(value = "application_website_director_phone")
    private String appWebDirectorTel;

    @TableField(value = "application_website_name")
    private String appWebsiteName;

    @TableField(value = "application_website_name_old")
    private String appWebsiteNameOld;

    @TableField(value = "application_website_domain_name")
    private String appWebsiteDomain;

    @TableField(value = "application_website_domain_name_old")
    private String appWebsiteDomainOld;

    @TableField(value = "application_website_ip")
    private String appWebsiteIp;

    @TableField(value = "application_website_ip_old")
    private String appWebsiteIpOld;

    @TableField(value = "application_visit_range")
    private Integer appVisitRange;

    @TableField(value = "application_service_content")
    private String appServiceContent;

    @TableField(value = "application_department_id")
    private String appWebsiteSpace;

    @TableField(value = "application_website_port")
    private String appWebsitePort;

    @TableField(value = "application_website_type")
    private String appWebsiteType;

    @TableField(value = "application_website_database")
    private String appWebsiteDB;

    @TableField(value = "application_interactive_column")
    private Integer appIntColumn;

    @TableField(value = "application_safety_audit")
    private Integer appSafetyAudit;

    @TableField(value = "application_submit_time")
    private Date appSubmitTime;

    @TableField(value = "application_state")
    private Integer appState;
}
