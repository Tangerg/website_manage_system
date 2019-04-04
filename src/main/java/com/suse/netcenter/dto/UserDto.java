package com.suse.netcenter.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * @author Tangerg
 * @create 2019-03-30 16:28
 */
@Data
public class UserDto {

    @NotBlank(message = "工号不能为空")
    private String userJobNum;
    @NotBlank(message = "密码不能为空")
    private String userPassword;
}
