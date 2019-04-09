package com.suse.netcenter.interceptor;

import com.suse.netcenter.entity.Log;
import com.suse.netcenter.service.impl.InformationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Tangerg
 * @create 2019-04-09 15:14
 */
public class LogInterceptor implements HandlerInterceptor {

    @Autowired
    InformationImpl informationImpl;

    private Log log;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ip = request.getRemoteAddr();
        log.setLogIp(StringUtils.isEmpty(ip) ? "0.0.0.0" : ip);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
