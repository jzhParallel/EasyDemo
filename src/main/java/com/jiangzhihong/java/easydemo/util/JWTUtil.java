package com.jiangzhihong.java.easydemo.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: EasyDemo
 * @description: jwt工具类
 * @author: jiangzhihong
 * @create: 2023-08-09 17:12
 **/
public class JWTUtil {
    private static Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    /**
     * 为用户id为uid的用户创建有效期为time毫秒的token
     *
     * @param uid
     * @param time
     * @return
     */
    public static String createToken(Long uid, String account, long time) {
        Map<String, Object> chain = new HashMap<String, Object>();
        chain.put("userId", uid);
        chain.put("account", account);
        String token = Jwts.builder()
                //.setSubject("用于认证的token")//设置主题
                .signWith(key)//签发算法与密钥jwtToken
                .setClaims(chain)//唯一的body数据
                .setIssuedAt(new Date())//签发时间
                .setExpiration(new Date(System.currentTimeMillis() + time))
                .compact();
        return token;
    }

    public static Claims checkToken(String token) {
        try {
            Jwt<JwsHeader, Claims> parse = Jwts.parserBuilder()
                    .setSigningKey(key).build().parseClaimsJws(token);
            return parse.getBody();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    //获得Claims后 Long id = (Long) claims.get("userId");
}
