package com.suse.netcenter.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

/**
 * @author Tangerg
 * @create 2019-03-25 20:09
 */
@Data
@TableName(value = "count")
public class Count {

    @JsonIgnore
    @TableId(value = "count_id")
    private Integer countID;

    @TableField(value = "count_website")
    private Integer countWebsite;

    @TableField(value = "count_lan_html")
    private Integer countLanHtml;

    @TableField(value = "count_lan_php")
    private Integer countLanPhp;

    @TableField(value = "count_lan_asp")
    private Integer countLanAsp;

    @TableField(value = "count_lan_jsp")
    private Integer countLanJsp;

    @TableField(value = "count_lan_other")
    private Integer countLanOther;

    @TableField(value = "count_db_no")
    private Integer countDBNo;

    @TableField(value = "count_db_mysql")
    private Integer countDBMysql;

    @TableField(value = "count_db_access")
    private Integer countDBAccess;

    @TableField(value = "count_db_mssql")
        private Integer countDBMSSql;

    @TableField(value = "count_db_oracle")
    private Integer countDBOracle;

    @TableField(value = "count_db_other")
    private Integer countDBOther;

    @TableField(value = "count_bug_sql")
    private Integer countBugSql;

    @TableField(value = "count_bug_xss")
    private Integer countBugXss;

    @TableField(value = "count_bug_csrf")
    private Integer countBugCsrf;

    @TableField(value = "count_bug_file_upload")
    private Integer countBugFileUp;

    @TableField(value = "count_bug_jurisdiction")
    private Integer countBugJurisdiction;

    @TableField(value = "count_bug_other")
    private Integer countBugOther;

    @TableField(value = "count_department")
    private Integer countDept;

    @JsonIgnore
    @TableField(value = "count_update_time")
    private Date countUpdateTime;
}
