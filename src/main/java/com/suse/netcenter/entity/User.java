package com.suse.netcenter.entity;

import lombok.Data;

/**
 * @author Tangerg
 * @create 2019-03-25 20:08
 */
@Data
public class User {
    private Integer userId;
    private Integer userJobNum;
    private String userName;
    private String userPassword;
    private Integer userGender;
    private Integer userDeptId;
    private String userOfficePhone;
    private String userTel;
    private Integer userIsQuit;
    private Integer userRoles;
}
