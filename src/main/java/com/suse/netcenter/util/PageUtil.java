package com.suse.netcenter.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.suse.netcenter.dto.PageDto;

/**
 * @author Tangerg
 * @create 2019-04-04 15:32
 */
public class PageUtil {
    public PageDto createPageDto(IPage iPage){
        PageDto pageDto = new PageDto();
        pageDto.setCurrent(iPage.getCurrent());
        pageDto.setPages(iPage.getPages());
        pageDto.setSize(iPage.getSize());
        pageDto.setTotal(iPage.getTotal());
        return pageDto;
    }
}
