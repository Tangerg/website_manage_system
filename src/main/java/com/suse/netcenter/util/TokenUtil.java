package com.suse.netcenter.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.suse.netcenter.entity.User;

import java.util.Date;

/**
 * @author Tangerg
 * @create 2019-03-28 15:14
 */
public class TokenUtil {
    //签发人
    private final static String Issuer = "SiChuanUniversity of Science & Engineering NetCenter";
    //私钥
    private final static String secret = "SiChuanUniversity of Science & Engineering Network management center";
    private Algorithm algorithm = Algorithm.HMAC256(secret);

    //生成token的方法
    public String createToken(User user) {
        //获取当前时间戳
        long nowMillis = System.currentTimeMillis();
        //过期时间1*24*60*60*1000ms即为一天时间
        long ttlMillis = 86400000;
        //设置具体过期时间
        long expMillis = nowMillis + ttlMillis;
        Date now = new Date(nowMillis);
        Date exp = new Date(expMillis);

        return JWT.create()
                .withIssuer(Issuer)
                .withIssuedAt(now)
                .withExpiresAt(exp)
                // 将 user的id和role和jobNum保存到 token 里面
                .withAudience(user.getUserId().toString(), user.getUserRoles().toString(), user.getUserJobNum().toString())
                .sign(algorithm);
    }

    //校验token的方法
    public boolean verifyToken(String token) {
        JWTVerifier jwtVerifier = JWT.require(algorithm)
                .withIssuer(Issuer)
                .build();
        try {
            jwtVerifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }
}
