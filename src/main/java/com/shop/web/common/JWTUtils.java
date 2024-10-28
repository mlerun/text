package com.shop.web.common;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class JWTUtils {
    //密钥
    private static final String SING = "cuit";
    /**
     * 生成token
     */
    public static String getToken(Map<String,String> map){
        Calendar instance = Calendar.getInstance();
        //默认7天过期
        instance.add(Calendar.DATE,7);
        //创建jwt builder
        JWTCreator.Builder builder = JWT.create();

        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });

        String token = builder.withExpiresAt(instance.getTime())//有效期
                .sign(Algorithm.HMAC256(SING));//密钥
        return token;
    }

    /**
     * 验证token合法性
     */
    public static DecodedJWT verify(String token){
        //返回验证结果（结果是内置的）
        return JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
    }

    public static Map<String, String> getClaims(String token) {
        // 解析 JWT Token
        DecodedJWT decodedJWT = JWT.decode(token);

        // 从 DecodedJWT 对象中获取声明信息
        Map<String, String> claimsMap = new HashMap<>();
        decodedJWT.getClaims().forEach((key, value) -> {
            claimsMap.put(key, value.asString());
        });

        return claimsMap;
    }
}