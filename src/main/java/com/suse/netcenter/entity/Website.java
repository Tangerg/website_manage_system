package com.suse.netcenter.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author Tangerg
 * @create 2019-03-25 20:08
 */
@Data
public class Website {
    private Integer WebsiteId;
    private Integer WebsiteDeptId;
    private Integer WebsiteDirectorNum;
    private String WebsiteName;
    private String WebsiteDomain;
    private String WebsiteIp;
    private Integer WebsiteVisitRange;
    private String WebsiteServiceContent;
    private String WebsiteSpace;
    private String WebsitePort;
    private String WebsiteType;
    private String WebsiteDB;
    private Integer WebsiteIntColumn;
    private Integer WebsiteSafetyAudit;
    private Integer WebsiteIsOpen;
    private Integer WebsiteIsRectify;
    private Integer WebsiteIsNotice;
    private String WebsiteNoticeType;
    private Date WebsiteCreateTime;
}
