package com.tensquare.jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 吴亚斌
 * create : 2019-03-06 11:08
 * description
 */
public class CreateJwt {
    public static void main(String[] args) {
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId("666")
                .setSubject("wu")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "root")
                .setExpiration(new Date(new Date().getTime()+60000))
                .claim("role","admin");//一分钟过期

        System.out.println(jwtBuilder.compact());
    }
}
