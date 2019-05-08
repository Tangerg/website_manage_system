package com.suse.netcenter.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
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
@TableName(value = "website")
public class Website {
    @TableId(value = "website_id")
    private Integer WebsiteId;

    @NotNull(message = "部门不能为空")
    @TableField(value = "website_department_id")
    private Integer WebsiteDeptId;

    @NotNull(message = "网站负责人工号不能为空")
    @Pattern(regexp = "^\\d{5}$", message = "工号格式不正确")
    @TableField(value = "website_director_num")
    private String WebsiteDirectorNum;

    @NotBlank(message = "网站名称不能为空")
    @TableField(value = "website_name")
    private String WebsiteName;

    @NotBlank(message = "网站域名不能为空")
    @Pattern(regexp = "^(?=^.{3,255}$)(http(s)?:\\/\\/)?(www\\.)?[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+(:\\d+)*(\\/\\w+\\.\\w+)*([\\?&]\\w+=\\w*)*$", message = "域名格式不正确")
    @TableField(value = "website_domain_name")
    private String WebsiteDomain;

    @NotBlank(message = "网站ip不能为空")
    @Pattern(regexp = "(?=(\\b|\\D))(((\\d{1,2})|(1\\d{1,2})|(2[0-4]\\d)|(25[0-5]))\\.){3}((\\d{1,2})|(1\\d{1,2})|(2[0-4]\\d)|(25[0-5]))(?=(\\b|\\D))$", message = "ip格式不正确")
    @TableField(value = "website_ip")
    private String WebsiteIp;

    @NotNull(message = "访问范围不能为空")
    @TableField(value = "website_visit_range")
    private Integer WebsiteVisitRange;

    @NotBlank(message = "服务内容不能为空")
    @TableField(value = "website_service_content")
    private String WebsiteServiceContent;

    @NotNull(message = "网站空间不能为空")
    @TableField(value = "website_space_type")
    private Integer WebsiteSpaceType;

    @TableField(value = "website_space_campus")
    private String WebsiteSpaceCampus;

    @TableField(value = "website_space_building")
    private String WebsiteSpaceBuilding;

    @TableField(value = "website_space_room")
    private String WebsiteSpaceRoom;

    @TableField(value = "website_port_in")
    private Integer WebsiteInPort;

    @TableField(value = "website_port_out")
    private Integer WebsiteOutPort;

    @NotNull(message = "网站语言类型不能为空")
    @TableField(value = "website_lan_type")
    private Integer WebsiteLanType;

    @TableField(value = "website_lan_other")
    private String WebsiteLanOther;

    @NotNull(message = "数据库类型不能为空")
    @TableField(value = "website_db_type")
    private Integer WebsiteDBType;

    @TableField(value = "website_db_other")
    private String WebsiteDBOther;

    @NotNull(message = "交互栏目不能为空")
    @TableField(value = "website_interactive_column")
    private Integer WebsiteIntColumn;

    @NotNull(message = "安全审核不能为空")
    @TableField(value = "website_safety_audit")
    private Integer WebsiteSafetyAudit;

    @NotNull(message = "是否开放不能为空")
    @TableField(value = "website_is_open")
    private Integer WebsiteIsOpen;

    @NotNull(message = "是否整改为空")
    @TableField(value = "website_is_rectify")
    private Integer WebsiteIsRectify;

    @NotNull(message = "是否通报为空")
    @TableField(value = "website_is_notice")
    private Integer WebsiteIsNotice;

    @TableField(value = "website_notice_type")
    private Integer WebsiteNoticeType;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "website_create_time")
    private Date WebsiteCreateTime;

    @NotNull(message = "是否认领为空")
    @TableField(value = "website_is_master")
    private Integer WebsiteIsMaster;

    @TableField(exist = false)
    private String WebsiteDirectorName;

    @TableField(exist = false)
    private String WebsiteDeptName;
}
