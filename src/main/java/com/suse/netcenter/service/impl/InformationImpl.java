package com.suse.netcenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.suse.netcenter.dto.Msg;
import com.suse.netcenter.entity.Count;
import com.suse.netcenter.entity.Log;
import com.suse.netcenter.mapper.CountMapper;
import com.suse.netcenter.mapper.LogMapper;
import com.suse.netcenter.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Tangerg
 * @create 2019-03-27 11:10
 */
@Service
public class InformationImpl implements InformationService {
    @Autowired
    LogMapper logMapper;
    @Autowired
    CountMapper countMapper;
    @Autowired
    WebsiteImpl websiteImpl;
    @Autowired
    DepartmentImpl departmentImpl;

    @Override
    public Msg infoAllWebsite() {
        return Msg.success().addData("infoCount", selectLastRecord());
    }

    @Override
    public Msg infoLog() {
        try {
            List<Log> logList = logMapper.selectList(null);
            return Msg.success().addData("info", logList);
        } catch (Exception e) {
            throw new RuntimeException("发生错误");
        }
    }

    public void addNewRecord() {
        newCountRecord(infoCount());
    }

    public void updateRecord() {
        updateRecord(selectLastRecord());
    }

    private void newCountRecord(Count count) {
        count.setId(0);
        count.setUpdateTime(new Date());
        try {
            countMapper.insert(count);
        } catch (Exception e) {
            throw new RuntimeException("操作失败");
        }
    }

    private Count selectLastRecord() {
        try {
            return countMapper.selectOne(new QueryWrapper<Count>().orderByDesc("count_id").last("limit 0,1"));
        } catch (Exception e) {
            throw new RuntimeException("操作失败");
        }
    }

    private void updateRecord(Count count) {
        count.setUpdateTime(new Date());
        try {
            countMapper.updateById(count);
        } catch (Exception e) {
            throw new RuntimeException("操作失败");
        }
    }

    private Count infoCount() {

        Count count = new Count();

        count.setWebsite(websiteImpl.countWebsite());
        count.setDepartment(departmentImpl.countDept());

        count.setBugSql(websiteImpl.countWebsiteByCondition("website_notice_type", 1, false));
        count.setBugXss(websiteImpl.countWebsiteByCondition("website_notice_type", 2, false));
        count.setBugCsrf(websiteImpl.countWebsiteByCondition("website_notice_type", 3, false));
        count.setBugFileUpload(websiteImpl.countWebsiteByCondition("website_notice_type", 4, false));
        count.setBugJurisdiction(websiteImpl.countWebsiteByCondition("website_notice_type", 5, false));
        count.setBugOther(websiteImpl.countWebsiteByCondition("website_notice_type", 6, true));
        count.setBugCount();

        count.setDbOther(websiteImpl.countWebsiteByCondition("website_database", 6, true));
        count.setDbOracle(websiteImpl.countWebsiteByCondition("website_database", 5, false));
        count.setDbAccess(websiteImpl.countWebsiteByCondition("website_database", 3, false));
        count.setDbMSSql(websiteImpl.countWebsiteByCondition("website_database", 4, false));
        count.setDbMysql(websiteImpl.countWebsiteByCondition("website_database", 2, false));
        count.setDbNo(websiteImpl.countWebsiteByCondition("website_database", 1, false));

        count.setLanHtml(websiteImpl.countWebsiteByCondition("website_type", 1, false));
        count.setLanPhp(websiteImpl.countWebsiteByCondition("website_type", 2, false));
        count.setLanAsp(websiteImpl.countWebsiteByCondition("website_type", 3, false));
        count.setLanJsp(websiteImpl.countWebsiteByCondition("website_type", 4, false));
        count.setLanOther(websiteImpl.countWebsiteByCondition("website_type", 5, true));

        return count;
    }

}
