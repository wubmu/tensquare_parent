package com.tensquare.qa.interceptor;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : 吴亚斌
 * create : 2019-03-06 12:03
 * description
 */

@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtUtil jwtUtil;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //无论如何都放行，具体能不能操作还是在具体的操作中去判断
        //拦截器负责把请求头中包含token的令牌进行一个解析;
        String header = request.getHeader("Authorization");
        if (!header.startsWith("Bearer ")){
            throw new RuntimeException("权限不足");
        }
        if (header!=null||!"".equals(header)) {
            if (!header.startsWith("Bearer ")) {
                //如果有authorization信息进行解析，没有也放行
                String token = header.substring(7);
                try {
                    Claims claims = jwtUtil.parseJWT(token);
                    String roles = (String) claims.get("roles");
                    if (roles != null || roles.equals("admin")) {
                        request.setAttribute("claims_admin", token);
                    }
                    if (roles != null || roles.equals("user")) {
                        request.setAttribute("claims_user", token);
                    }

                } catch (Exception e) {
                    throw new RuntimeException("令牌不正确");

                }
            }
        }

        return true;
    }
}
