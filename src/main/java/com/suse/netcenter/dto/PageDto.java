package com.suse.netcenter.dto;

import lombok.Data;

/**
 * @author Tangerg
 * @create 2019-03-31 19:51
 */
@Data
public class PageDto {
    private Long total;
    private Long pages;
    private Long current;
    private Long size;
}
