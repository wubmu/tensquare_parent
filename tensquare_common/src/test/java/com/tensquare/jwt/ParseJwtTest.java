package com.tensquare.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.text.SimpleDateFormat;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 吴亚斌
 * create : 2019-03-06 11:11
 * description
 */
public class ParseJwtTest {
    public static void main(String[] args) {
        Claims claims = Jwts.parser().setSigningKey("root").
                parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2NjYiLCJzdWIiOiJ3dSIsImlhdCI6MTU1MTg0MzMxMCwiZXhwIjoxNTUxODQzMzcwLCJyb2xlIjoiYWRtaW4ifQ.i6tHx8KpiMNtOgNXGj6v8Yz5x4CCfmZdgt-Q8sapcWY")
                .getBody();
        System.out.println("用户id:"+claims.getId());
        System.out.println("用户名称:"+claims.getSubject());
        System.out.println("登录时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(claims.getIssuedAt()));
        System.out.println("过期时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(claims.getExpiration()));
        System.out.println("用户角色:"+claims.get("role"));

    }
}
