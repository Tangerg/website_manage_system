package com.suse.netcenter.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.suse.netcenter.entity.User;

/**
 * @author Tangerg
 * @create 2019-03-28 15:14
 */
public class TokenUtil {
    private final static String secret = "SichuanUniversity of Science & Engineering NetCenter";

    public String getToken(User user) {
        String token = "";
        token = JWT.create()
                .withAudience(user.getUserId().toString())
                .withAudience(user.getUserRoles().toString())
                .withAudience(user.getUserJobNum().toString())// 将 user的id和role和jobNum保存到 token 里面
                .sign(Algorithm.HMAC256(secret));// 以 secret 的密钥
        return token;
    }

    public Boolean verifyToken(String token) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).build();
        System.out.println(jwtVerifier.toString());
        try {
            jwtVerifier.verify(token);
            return true;
        }catch (JWTVerificationException e){
            return false;
        }
    }
}
