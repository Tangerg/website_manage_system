package com.suse.netcenter.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.suse.netcenter.annotation.PassToken;
import com.suse.netcenter.annotation.UserLoginToken;
import com.suse.netcenter.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Tangerg
 * @create 2019-03-28 15:26
 * jwt拦截器
 */
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();

        /*
         * 只存在PassToken和UserLoginToken两种情况
         *
         */
        //检查是否有PassToken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //检查是否有UserLoginToken的注解，有则检查token
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            String token = request.getHeader("token");// 从 http 请求头中取出 token
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            // 执行认证
            if (userLoginToken.required()) {
                // 如果token为空
                if (token == null || token.equals("")) {
                    throw new RuntimeException("没有token，请重新登录");
                }
                TokenUtil tokenUtil = new TokenUtil();
                if (tokenUtil.verifyToken(token)) {
                    // 获取 token 中的信息
                    Map data = new HashMap<String, Claim>();
                    String userId;
                    String userName;
                    String userJobNum;
                    try {
                        userId = JWT.decode(token).getAudience().get(0);
                        userName = JWT.decode(token).getAudience().get(1);
                        userJobNum = JWT.decode(token).getAudience().get(2);
                        System.out.println(userId);
                        System.out.println(userName);
                        System.out.println(userJobNum);
                    } catch (JWTDecodeException j) {
                        throw new RuntimeException("令牌解析错误，请重新登录");
                    }
                } else {
                    throw new RuntimeException("token错误或已过期，请重新登录");
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
