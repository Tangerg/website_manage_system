package com.suse.netcenter.dto;

import lombok.Data;


/**
 * @author Tangerg
 * @create 2019-04-04 16:48
 */
@Data
public class UserBaseDto {

    private Integer userId;

    private String userJobNum;

    private String userName;

    private Integer userRoles;
}
