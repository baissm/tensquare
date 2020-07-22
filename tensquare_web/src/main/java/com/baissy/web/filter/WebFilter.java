package com.baissy.web.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author chenlin
 * @create 2020/7/1/13:54
 */
@Component
public class WebFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("经过了前台过滤器");
        //经过zuul网关转发到对应的应用会丢失header头信息，需要将头信息一起转发到对应的应用中
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String authorization = request.getHeader("Authorization");
        if(authorization!=null && !"".equals(authorization)){
            currentContext.addZuulRequestHeader("Authorization",authorization);
        }
        return null;
    }
}
