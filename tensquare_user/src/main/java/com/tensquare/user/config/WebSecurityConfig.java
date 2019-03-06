package com.tensquare.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 安全配置类
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //authorizeRequests所有security安全注解配置的开端，表明需要权限
        //需要的权限分为两部分，一部分是拦截路径，第二部分访问该路径需要的权限
        //antMatchers表示拦截什么，permitALL任何权限都可以访问
        //anyRequest()任何请求,authenticated认证后才能访问
        //.and().csrf().disable();固定写法，表示csrf请求拦截失效
        http
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
    }
}
