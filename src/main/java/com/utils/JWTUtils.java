package com.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Calendar;
import java.util.Map;

/**
 * 汪玉龙
 */
public class JWTUtils {

    private static final String SIGN = "!Q@W3e4r%T^Y";

    /**
     * 获取token
     * @param paramMap 参数map
     * @return 访问token
     */
    public static String getToken(Map<String, String> paramMap) {
        Calendar instance =  Calendar.getInstance();
        instance.add(Calendar.SECOND, 10);

        JWTCreator.Builder builder = JWT.create();
        paramMap.forEach((k, v) -> {
            builder.withClaim(k,v);
        });
        return builder.withExpiresAt(instance.getTime()).sign(Algorithm.HMAC256(SIGN));
    }

    public static DecodedJWT verify(String token) {
         return JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
    }
}
