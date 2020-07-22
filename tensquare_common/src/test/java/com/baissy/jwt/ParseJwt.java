package com.baissy.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.text.SimpleDateFormat;

/**
 * @author chenlin
 * @create 2020/6/5/13:58
 */
public class ParseJwt {
    public static void main(String[] args) {
        Claims claims = Jwts.parser().setSigningKey("baissy")
            .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2NjYiLCJzdWIiOiJhYSIsImlhdCI6MTU5MTMzNzYwNCwiZXhwIjoxNTkxMzM3NjM0LCJyb2xlIjoiYWRtaW4ifQ.Ist6Kdee6SW8Fu1mrlm8OHGFgkwc3vyNiKE1sXiSbiQ")
            .getBody();
        System.out.println("用户id："+claims.getId()
            +"用户名："+claims.getSubject()
            +"登陆时间："+new SimpleDateFormat("yyy-MM-dd HH:mm:ss").format(claims.getIssuedAt())
            +"过期时间："+new SimpleDateFormat("yyy-MM-dd HH:mm:ss").format(claims.getExpiration())
            +"用户角色："+claims.get("role")
        );
    }
}
