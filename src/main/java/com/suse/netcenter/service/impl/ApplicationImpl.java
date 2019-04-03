package com.suse.netcenter.service.impl;

import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.suse.netcenter.dto.Msg;
import com.suse.netcenter.entity.Application;
import com.suse.netcenter.mapper.ApplicationMapper;
import com.suse.netcenter.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
                return Msg.fail().addMsg("申请保存失败");
            }
        } catch (Exception e) {
            throw new RuntimeException("申请保存失败");
        }
        return Msg.success().addMsg("申请保存成功");
    }

    @Override
    public Msg ApplicationQueryAll(String condition) {
        List<Application> applicationList = new ArrayList<Application>();
        switch (condition) {
            case "1":
                applicationList = applicationMapper.selectList(new QueryWrapper<Application>().ne("application_state", 10));
                break;
            case "2":
                applicationList = applicationMapper.selectList(new QueryWrapper<Application>().eq("application_state", 20));
                break;
            case "3":
                applicationList = applicationMapper.selectList(new QueryWrapper<Application>().eq("application_state", 30));
                break;
            case "4":
                applicationList = applicationMapper.selectList(new QueryWrapper<Application>().eq("application_state", 40));
                break;
            default:
                applicationList = applicationMapper.selectList(new QueryWrapper<Application>().ne("application_state", 10));

        }
        return Msg.success().addData("applicationList", applicationList);
    }

    @Override
    public Msg ApplicationQuery(Integer id, String token) {
        Application application = new Application();
        String userJobNum = JWT.decode(token).getAudience().get(2);
        String userRoles = JWT.decode(token).getAudience().get(1);
        try {
            application = applicationMapper.selectById(id);
            if (application == null) {
                return Msg.fail().addMsg("该申请不存在");
            }
            if (application.getAppWebDirectorNum().toString().equals(userJobNum) || userRoles.equals("1")) {
                return Msg.success().addData("application", application);
            } else {
                return Msg.fail().addMsg("你无权查看本申请");
            }
        } catch (Exception e) {
            throw new RuntimeException("查询失败");
        }
    }

    @Override
    public Msg ApplicationSubmit(Integer id, Application application, String token) {
        String userJobNum = JWT.decode(token).getAudience().get(2);
        if (id.equals(application.getAppId()) && application.getAppWebDirectorNum().toString().equals(userJobNum) &&application.getAppState().equals(10)) {
            application.setAppSubmitTime(new Date());
            application.setAppState(20);
            Application application1 = applicationMapper.selectById(id);
            if (application1.getAppState().equals(10)) {
                try {
                    if (applicationMapper.updateById(application) == 0) {
                        return Msg.fail().addMsg("提交失败");
                    }
                    applicationMapper.updateById(application);
                    return Msg.success().addMsg("提交成功");
                } catch (Exception e) {
                    throw new RuntimeException("提交失败");
                }
            }
        }
        return Msg.fail().addMsg("该申请已提交，请勿重复提交");
    }

    @Override
    public Msg ApplicationUpdate(Integer id, Application application, String token) {
        String userJobNum = JWT.decode(token).getAudience().get(2);
        if (id.equals(application.getAppId()) && application.getAppWebDirectorNum().toString().equals(userJobNum) && application.getAppState().equals(10)) {
            Application application1 = applicationMapper.selectById(id);
            if (application1.getAppState().equals(10)) {
                try {
                    if (applicationMapper.updateById(application) == 0) {
                        return Msg.fail().addMsg("修改失败");
                    }
                    return Msg.success().addMsg("修改成功");
                } catch (Exception e) {
                    throw new RuntimeException("修改失败");
                }
            }
        }
        return Msg.fail().addMsg("你不能修改该申请");
    }

    @Override
    public Msg ApplicationReview(Integer id, Application application) {
        if (id.equals(application.getAppId())) {
            try {
                if (applicationMapper.updateById(application) == 0) {
                    return Msg.fail().addMsg("操作失败");
                }
                return Msg.success().addMsg("操作成功");
            } catch (Exception e) {
                throw new RuntimeException("操作失败");
            }
        }
        return Msg.fail().addMsg("操作失败");
    }

}
