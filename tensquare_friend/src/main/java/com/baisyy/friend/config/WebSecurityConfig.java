package com.baisyy.friend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 安全配置类（拦截器）
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            //授权要求
                .authorizeRequests()
            //拦截的路径 ， 允许全部
                .antMatchers("/**").permitAll()
            //任何请求， 认证后才能访问
                .anyRequest().authenticated()
            //固定写法，使csrf拦截失效
                .and().csrf().disable();
    }
}
