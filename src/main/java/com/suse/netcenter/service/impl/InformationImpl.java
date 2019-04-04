package com.suse.netcenter.service.impl;

import com.suse.netcenter.dto.Msg;
import com.suse.netcenter.entity.Count;
import com.suse.netcenter.entity.Log;
import com.suse.netcenter.mapper.CountMapper;
import com.suse.netcenter.mapper.LogMapper;
import com.suse.netcenter.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tangerg
 * @create 2019-03-27 11:10
 */
@Service
public class InformationImpl implements InformationService {
    @Autowired
    CountMapper countMapper;
    @Autowired
    LogMapper logMapper;
    @Override
    public Msg infoAllWebsite() {
        try {
            Count count=countMapper.selectById(1);
            return Msg.success().addData("info",count);
        }catch (Exception e){
            throw new RuntimeException("发生错误");
        }
    }

    @Override
    public Msg infoLog() {
        try {
            List<Log> logList=logMapper.selectList(null);
            return Msg.success().addData("info",logList);
        }catch (Exception e){
            throw new RuntimeException("发生错误");
        }
    }
}
