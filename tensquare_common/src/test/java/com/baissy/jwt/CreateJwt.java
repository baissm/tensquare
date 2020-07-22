package com.baissy.jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @author chenlin
 * @create 2020/6/5/13:44
 */
public class CreateJwt {
    public static void main(String[] args) {
        JwtBuilder jwt = Jwts.builder()
            .setId("666")
            .setSubject("aa")
            .setIssuedAt(new Date())
            .signWith(SignatureAlgorithm.HS256,"baissy")
        .setExpiration(new Date(new Date().getTime()+30000))
        .claim("role","admin");
        System.out.println(jwt.compact());
    }
}
