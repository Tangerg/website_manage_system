package com.suse.netcenter.service;

import org.springframework.stereotype.Service;

@Service
public interface LogService {
    void addLog(String JobNum,String ip,String path);
}
