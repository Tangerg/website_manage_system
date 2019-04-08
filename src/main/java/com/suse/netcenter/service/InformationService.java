package com.suse.netcenter.service;

import com.suse.netcenter.dto.Msg;
import org.springframework.stereotype.Service;

/**
 * @author Tangerg
 * @create 2019-03-27 11:08
 */
@Service
public interface InformationService {
    Msg infoAllWebsite();
    Msg infoLog();
}
