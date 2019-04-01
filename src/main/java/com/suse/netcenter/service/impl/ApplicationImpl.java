package com.suse.netcenter.service.impl;

import com.suse.netcenter.dto.Msg;
import com.suse.netcenter.entity.Application;
import com.suse.netcenter.mapper.ApplicationMapper;
import com.suse.netcenter.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Tangerg
 * @create 2019-03-27 11:09
 */
@Service
public class ApplicationImpl implements ApplicationService {
    @Autowired
    ApplicationMapper applicationMapper;

    @Override
    public Msg ApplicationAdd(Application application) {
        application.setAppId(0);
        try {
            if (applicationMapper.insert(application) == 0) {
                return Msg.success().addMsg("申请保存失败");
            }
        } catch (Exception e) {
            throw new RuntimeException("申请保存失败1");
        }
        return Msg.success().addMsg("申请保存成功");
    }
}
