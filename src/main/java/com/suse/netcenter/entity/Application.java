package com.suse.netcenter.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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

    @NotNull(message = "部门不能为空")
    @TableField(value = "application_department_id")
    private Integer appDeptId;

    @NotNull(message = "备案操作不能为空")
    @TableField(value = "application_record_operate")
    private Integer appRecOperate;

    @NotNull(message = "部门负责人工号不能为空")
    @TableField(value = "application_department_director")
    private String appDeptDirector;

    @NotNull(message = "办公电话不能为空")
    @TableField(value = "application_office_phone")
    private String appOfficePhone;

    @NotNull(message = "网站负责人工号不能为空")
    @TableField(value = "application_website_director_num")
    private String appWebDirectorNum;

    @TableField(value = "application_website_director_name")
    private String appWebDirectorName;

    @NotNull(message = "网站负责人手机号不能为空")
    @Pattern(regexp = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$", message = "手机号格式不正确")
    @TableField(value = "application_website_director_phone")
    private String appWebDirectorTel;

    @NotBlank(message = "网站名称不能为空")
    @TableField(value = "application_website_name")
    private String appWebsiteName;

    @TableField(value = "application_website_name_old")
    private String appWebsiteNameOld;

    @NotBlank(message = "网站域名不能为空")
    @Pattern(regexp = "[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(/.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+/.?", message = "域名格式不正确")
    @TableField(value = "application_website_domain_name")
    private String appWebsiteDomain;
    
    @Pattern(regexp = "[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(/.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+/.?", message = "域名格式不正确")
    @TableField(value = "application_website_domain_name_old")
    private String appWebsiteDomainOld;

    @NotBlank(message = "网站ip不能为空")
    @Pattern(regexp = "((?:(?:25[0-5]|2[0-4]\\\\d|[01]?\\\\d?\\\\d)\\\\.){3}(?:25[0-5]|2[0-4]\\\\d|[01]?\\\\d?\\\\d))", message = "ip格式不正确")
    @TableField(value = "application_website_ip")
    private String appWebsiteIp;

    @Pattern(regexp = "((?:(?:25[0-5]|2[0-4]\\\\d|[01]?\\\\d?\\\\d)\\\\.){3}(?:25[0-5]|2[0-4]\\\\d|[01]?\\\\d?\\\\d))", message = "ip格式不正确")
    @TableField(value = "application_website_ip_old")
    private String appWebsiteIpOld;

    @NotNull(message = "访问范围不能为空")
    @TableField(value = "application_visit_range")
    private Integer appVisitRange;

    @NotBlank(message = "服务内容不能为空")
    @TableField(value = "application_service_content")
    private String appServiceContent;

    @NotNull(message = "网站空间不能为空")
    @TableField(value = "application_website_space_type")
    private Integer appWebsiteSpaceType;

    @TableField(value = "application_website_space_campus")
    private String appWebsiteSpaceCampus;

    @TableField(value = "application_website_space_building")
    private String appWebsiteSpaceBuilding;

    @TableField(value = "application_website_space_room")
    private String appWebsiteSpaceRoom;

    @TableField(value = "application_website_port_in")
    @NotNull(message = "校内端口不能为空")
    private Integer appWebsiteInPort;

    @TableField(value = "application_website_port_out")
    @NotNull(message = "校外端口不能为空")
    private Integer appWebsiteOutPort;

    @NotBlank(message = "网站语言不能为空")
    @TableField(value = "application_website_type")
    private String appWebsiteType;

    @NotBlank(message = "数据库类型为空")
    @TableField(value = "application_website_database")
    private String appWebsiteDB;

    @NotNull(message = "交互栏目不能为空")
    @TableField(value = "application_interactive_column")
    private Integer appIntColumn;

    @NotNull(message = "安全审核不能为空")
    @TableField(value = "application_safety_audit")
    private Integer appSafetyAudit;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "application_submit_time")
    private Date appSubmitTime;

    @TableField(value = "application_state")
    private Integer appState;
}
