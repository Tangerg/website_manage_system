package com.suse.netcenter.service;

import com.suse.netcenter.dto.Msg;
import com.suse.netcenter.entity.Website;
import org.springframework.stereotype.Service;

/**
 * @author Tangerg
 * @create 2019-04-03 16:34
 */
@Service
public interface WebsiteService {
    Msg queryWebsiteAll(Integer pageNum,Integer pageSize);
    Msg queryWebsite(Integer id);

    Msg updateWebsite(Integer id, Website website);
}
