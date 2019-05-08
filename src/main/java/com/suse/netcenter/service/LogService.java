package com.suse.netcenter.service;

import org.springframework.stereotype.Service;

@Service
public interface LogService {
    void addLog(String JobNum,String name,String ip,String path,String operate);
}
