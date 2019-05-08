package com.suse.netcenter.service.impl;

import com.suse.netcenter.entity.Log;
import com.suse.netcenter.mapper.LogMapper;
import com.suse.netcenter.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

@Service
public class LogImpl implements LogService {
    @Autowired
    LogMapper logMapper;

    @Override
    public void addLog(String JobNum, String name, String ip, String path,String operate) {
        if (JobNum == null) {
            return;
        }
        Log log = new Log();
        log.setLogId(0);
        log.setLogIp(StringUtils.isEmpty(ip) ? "0.0.0.0" : ip);
        log.setLogPath(StringUtils.isEmpty(ip) ? "/" : path);
        log.setLogOperate(operate);
        log.setLogJobNum(JobNum);
        log.setLogName(name);
        log.setLogCreateTime(new Date());
        logMapper.insert(log);
    }
}
