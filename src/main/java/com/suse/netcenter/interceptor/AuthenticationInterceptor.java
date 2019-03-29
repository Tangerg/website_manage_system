package com.suse.netcenter.interceptor;

import com.auth0.jwt.JWT;
import com.suse.netcenter.annotation.AdminToken;
import com.suse.netcenter.annotation.PassToken;
import com.suse.netcenter.annotation.UserLoginToken;
import com.suse.netcenter.util.TokenUtil;
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
        String token = request.getHeader("token");// 从 http 请求头中取出 token
        //检查是否有UserLoginToken的注解，有则检查token
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            // 执行认证
            if (userLoginToken.required()) {
                // 如果token为null或者"","       "
                if (token == null || token.isEmpty()) {
                    throw new RuntimeException("没有token，请重新登录");
                }
                TokenUtil tokenUtil = new TokenUtil();
                //如果token通过校验
                if (tokenUtil.verifyToken(token)) {
                    // 校验token携带的数据
                    String userId = JWT.decode(token).getAudience().get(0);
                    String userRoles = JWT.decode(token).getAudience().get(1);
                    String userJobNum = JWT.decode(token).getAudience().get(2);
                    if (userId == null || userId.isEmpty() || userRoles == null || userRoles.isEmpty() || userJobNum == null || userJobNum.isEmpty()) {
                        throw new RuntimeException("令牌解析错误，请重新登录");
                    }
                    //检查是否有AdminToken的注解，有则检查token
                    if (method.isAnnotationPresent(AdminToken.class)) {
                        AdminToken adminToken = method.getAnnotation(AdminToken.class);
                        // 执行认证
                        if (adminToken.required()) {
                            if (!userRoles.equals("1")) {
                                throw new RuntimeException("你没有权限");
                            }
                        }
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
