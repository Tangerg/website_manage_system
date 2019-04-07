package com.suse.netcenter.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author Tangerg
 * @create 2019-03-25 20:09
 */
@Data
public class Count {

    private Integer website;

    private Integer department;

    private Integer lanHtml;

    private Integer lanPhp;

    private Integer lanAsp;

    private Integer lanJsp;

    private Integer lanOther;

    private Integer dbNo;

    private Integer dbMysql;

    private Integer dbAccess;

    private Integer dbMSSql;

    private Integer dbOracle;

    private Integer dbOther;

    private Integer bugCount;

    private Integer bugSql;

    private Integer bugXss;

    private Integer bugCsrf;

    private Integer bugFileUpload;

    private Integer bugJurisdiction;

    private Integer bugOther;

}
