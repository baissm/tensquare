package com.baissy.user.interceptor;

import com.baissy.tensquare.entity.until.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author chenlin
 * @create 2020/6/5/15:35
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("经过了拦截器");
        String header =request.getHeader("Authorization");
        if(header!=null&&"".equals(header)){
            if(header.startsWith("Bearer ")){
                String token = header.substring(7);
                try{
                    Claims claims = jwtUtil.parseJWT(token);
                    String roles= (String) claims.get("roles");
                    if(roles!=null&& roles.equals("admin")){
                        request.setAttribute("claims_admin",token);
                    }
                    if(roles!=null&&roles.equals("user")){
                        request.setAttribute("claims_user",token);
                    }

                }catch (Exception e){
                    throw new RuntimeException("令牌不正确");
                }
            }
        }
        return true;
    }
}
