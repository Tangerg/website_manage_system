package com.suse.netcenter.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
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

/**
 * @author Tangerg
 * @create 2019-03-28 15:26
 * jwt拦截器
 */
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        String token = request.getHeader("token");// 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有PassToken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //检查有没有UserLoginToken的注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            // 执行认证
            if (userLoginToken.required()) {
                // 如果token为空
                if (token == null || token.equals("")) {
                    throw new RuntimeException("无token，请重新登录");
                }
                TokenUtil tu = new TokenUtil();
                Boolean flag = tu.verifyToken(token);
                if(flag){
                    // 获取 token 中的信息
                    String userId;
                    String userRoles;
                    String userJobNum;
                    try {
                        userId = JWT.decode(token).getAudience().get(0);
                        userRoles = JWT.decode(token).getAudience().get(1);
                        userJobNum = JWT.decode(token).getAudience().get(2);
                        System.out.println(userId);
                        System.out.println(userRoles);
                        System.out.println(userJobNum);
                        return true;
                    } catch (JWTDecodeException j) {
                        throw new RuntimeException("401");
                    }
                }else{
                    throw new RuntimeException("你没有权限");
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
