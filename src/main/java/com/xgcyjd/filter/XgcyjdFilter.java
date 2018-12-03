package com.xgcyjd.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@WebFilter(filterName = "XgcyjdFilter")
public class XgcyjdFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
//        HttpServletResponse httpResponse = (HttpServletResponse) res;
//        String [] allowDomain= {"http://132.12.11.11:8888","http://123.112.112.12:80","http://123.16.12.23","http://121.12.18.13:10195"};
//        Set allowedOrigins= new HashSet(Arrays.asList(allowDomain));
//       String originHeader=((HttpServletRequest) req).getHeader("Origin");
//        if (allowedOrigins.contains(originHeader)){
        ((HttpServletResponse) res).setHeader("Access-Control-Allow-Origin", "*");
//        ((HttpServletResponse) res).setContentType("application/json;charset=UTF-8");
        ((HttpServletResponse) res).setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//        ((HttpServletResponse) res).setHeader("Access-Control-Max-Age", "3600");
        ((HttpServletResponse) res).setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
//         ((HttpServletResponse) res).setHeader("Access-Control-Allow-Credentials", "true");
            // 如果要把Cookie发到服务器，需要指定Access-Control-Allow-Credentials字段为true;
//       ((HttpServletResponse) res).setHeader("XDomainRequestAllowed","1");
            chain.doFilter(req, res);
           return;

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
