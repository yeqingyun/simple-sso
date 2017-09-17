package com.gionee.sso.filter;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yeqy on 2017/9/13.
 */
public class AuthFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //User user = (User) request.getSession().getAttribute("user");
        String redirect = request.getParameter("redirect");
        Cookie[] cs = request.getCookies();
        Cookie tc = null;
        if(cs != null) {
            for (Cookie c : cs) {
                if (c.getName().equals("token")) {
                    tc = c;
                }
            }
        }
        if (tc != null) {
            if (redirect != null && redirect.trim().length() > 0) {
                if (redirect.endsWith("/")) {
                    response.sendRedirect(redirect.substring(0, redirect.length() - 1) + "?token="+tc.getValue());
                }
            }
        } else if (request.getRequestURI().indexOf("login") > 0 || request.getRequestURI().indexOf("auth") > 0) {//登录过或者是执行登录操作
            filterChain.doFilter(request, response);
        } else {//未登录
            StringBuilder sb = new StringBuilder("/login.jsp");
            if (redirect != null && redirect.trim().length() > 0)
                sb.append("?redirect=").append(redirect);
            response.sendRedirect(sb.toString());
        }
    }

    public void destroy() {

    }
}
