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
    Msg ApplicationQueryAll(String condition,Integer pageNum,Integer pageSize);
    Msg ApplicationSubmit(Application application,String token);
    Msg ApplicationReview(Integer id,Application application);
    Msg ApplicationQuery(String JobNum, String condition,Integer pageNum,Integer pageSize,String token);
}
