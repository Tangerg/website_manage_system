package com.suse.netcenter.service;

import com.suse.netcenter.dto.Msg;
import com.suse.netcenter.entity.Application;
import org.springframework.stereotype.Service;

/**
 * @author Tangerg
 * @create 2019-03-27 11:06
 */
@Service
public interface ApplicationService {
    Msg ApplicationAdd(Application application);
    Msg ApplicationQueryAll(String condition);
    Msg ApplicationQuery(Integer id,String token);
    Msg ApplicationSubmit(Integer id,Application application,String token);
    Msg ApplicationUpdate(Integer id,Application application,String token);
    Msg ApplicationReview(Integer id,Application application);
}
