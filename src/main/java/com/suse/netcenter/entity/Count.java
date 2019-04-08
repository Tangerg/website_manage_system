package com.suse.netcenter.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author Tangerg
 * @create 2019-03-25 20:09
 */
@Data
@TableName(value = "count")
public class Count {

    @TableId(value = "count_id")
    private Integer id;

    @TableField(value = "count_website")
    private Integer website;

    @TableField(value = "count_department")
    private Integer department;

    @TableField(value = "count_lan_html")
    private Integer lanHtml;

    @TableField(value = "count_lan_php")
    private Integer lanPhp;

    @TableField(value = "count_lan_asp")
    private Integer lanAsp;

    @TableField(value = "count_lan_jsp")
    private Integer lanJsp;

    @TableField(value = "count_lan_other")
    private Integer lanOther;

    @TableField(value = "count_db_no")
    private Integer dbNo;

    @TableField(value = "count_db_mysql")
    private Integer dbMysql;

    @TableField(value = "count_db_access")
    private Integer dbAccess;

    @TableField(value = "count_db_mssql")
    private Integer dbMSSql;

    @TableField(value = "count_db_oracle")
    private Integer dbOracle;

    @TableField(value = "count_db_other")
    private Integer dbOther;

    @TableField(value = "count_bug_sql")
    private Integer bugSql;

    @TableField(value = "count_bug_xss")
    private Integer bugXss;

    @TableField(value = "count_bug_csrf")
    private Integer bugCsrf;

    @TableField(value = "count_bug_file_upload")
    private Integer bugFileUpload;

    @TableField(value = "count_bug_jurisdiction")
    private Integer bugJurisdiction;

    @TableField(value = "count_bug_other")
    private Integer bugOther;

    @TableField(value = "count_bug_total")
    private Integer bugCount;

    @TableField(value = "count_update_time")
    private Date updateTime;

    public void setBugCount() {
        this.bugCount = this.bugCsrf+this.bugFileUpload+this.bugJurisdiction+this.bugOther+this.bugSql;
    }


}
