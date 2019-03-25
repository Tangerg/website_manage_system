package com.suse.netcenter.entity;

import lombok.Data;

/**
 * @author Tangerg
 * @create 2019-03-25 20:09
 */
@Data
public class Count {
    private Integer countWebsite;
    private Integer countLanHtml;
    private Integer countLanPhp;
    private Integer countLanAsp;
    private Integer countLanJsp;
    private Integer countLanOther;
    private Integer countDBNo;
    private Integer countDBMysql;
    private Integer countDBAccess;
    private Integer countDBMssql;
    private Integer countDBOracle;
    private Integer countDBOther;
    private Integer countBugSql;
    private Integer countBugXss;
    private Integer countBugCsrf;
    private Integer countBugFileUp;
    private Integer countBugJurisdiction;
    private Integer countBugOther;
    private Integer countDept;
}
