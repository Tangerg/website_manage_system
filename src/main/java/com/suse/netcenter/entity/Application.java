package com.suse.netcenter.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author Tangerg
 * @create 2019-03-25 20:08
 */
@Data
public class Application {
    private Integer appId;
    private Integer appDeptId;
    private Integer appRecOperate;
    private Integer appDeptDirector;
    private String appOfficePhone;
    private Integer appWebDirectorNum;
    private String appWebDirectorName;
    private String appWebDirectorTel;
    private String appWebsiteName;
    private String appWebsiteNameOld;
    private String appWebsiteDomain;
    private String appWebsiteDomainOld;
    private String appWebsiteIp;
    private String appWebsiteIpOld;
    private Integer appVisitRange;
    private String appServiceContent;
    private String appWebsiteSpace;
    private String appWebsitePort;
    private String appWebsiteType;
    private String appWebsiteDB;
    private Integer appIntColumn;
    private Integer appSafetyAudit;
    private Date appSubmitTime;
    private String appState;
}
