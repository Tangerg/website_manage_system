package com.suse.netcenter.dto;

import lombok.Data;

/**
 * @author Tangerg
 * @create 2019-04-21 9:51
 */
@Data
public class PasswordDto {
    private String passwordOld;
    private String passwordNew;
}