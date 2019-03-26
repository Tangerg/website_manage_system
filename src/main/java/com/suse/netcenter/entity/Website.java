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
@TableName(value = "website")
public class Website {
    @TableId(value = "website_id")
    private Integer WebsiteId;

    @TableField(value = "website_department_id")
    private Integer WebsiteDeptId;

    @TableField(value = "website_director_num")
    private Integer WebsiteDirectorNum;

    @TableField(value = "website_name")
    private String WebsiteName;

    @TableField(value = "website_domain_name")
    private String WebsiteDomain;

    @TableField(value = "website_ip")
    private String WebsiteIp;

    @TableField(value = "website_visit_range")
    private Integer WebsiteVisitRange;

    @TableField(value = "website_service_content")
    private String WebsiteServiceContent;

    @TableField(value = "website_space")
    private String WebsiteSpace;

    @TableField(value = "website_port")
    private String WebsitePort;

    @TableField(value = "website_type")
    private String WebsiteType;

    @TableField(value = "website_database")
    private String WebsiteDB;

    @TableField(value = "website_interactive_column")
    private Integer WebsiteIntColumn;

    @TableField(value = "website_safety_audit")
    private Integer WebsiteSafetyAudit;

    @TableField(value = "website_is_open")
    private Integer WebsiteIsOpen;

    @TableField(value = "website_is_rectify")
    private Integer WebsiteIsRectify;

    @TableField(value = "website_is_notice")
    private Integer WebsiteIsNotice;

    @TableField(value = "website_notice_type")
    private String WebsiteNoticeType;

    @TableField(value = "website_create_time")
    private Date WebsiteCreateTime;
}
