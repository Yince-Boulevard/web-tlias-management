package com.yince;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTTest {

    @Test
    public void testGenerateJWT() {
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("id", 1);
        dataMap.put("username", "admin");
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "VmpGa2MyUldhM2xXVjJSU1lsUnJlRmxyWkZkTmJHeFpVMjF6UFE9PQ==") // 指定签名算法和密钥
                .addClaims(dataMap) // 添加自定义声明
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000)) // 设置过期时间
                .compact();// 生成JWT字符串
        System.out.println(jwt);
    }

    @Test
    public void testParseJWT() {
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsImV4cCI6MTc0MTA5MjIwOH0.Og0RXnQAV9FuuP91LluXZYW9POY3HiJuNGH1yo4ltPo";
        // 1. 指定秘钥
//        Claims claims = Jwts.parser()
//                .setSigningKey("VmpGa2MyUldhM2xXVjJSU1lsUnJlRmxyWkZkTmJHeFpVMjF6UFE9PQ==")
//                .parseClaimsJws(jwt)
//                .getBody();
//        System.out.println(claims);
    }
}
