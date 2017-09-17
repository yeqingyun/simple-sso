package com.gionee.sso.fileter;

import com.yeqy.sso.biz.User;
import com.yeqy.sso.util.HttpUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yeqy on 2017/9/12.
 */
public class ScurityFileter implements Filter {


    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;


        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {//存在会话(已登录过)
            filterChain.doFilter(request, response);
        } else {//未登录
            String token = request.getParameter("token");
            if (token != null && token.trim().length() > 0) {//有token 验证token
                try {
                    User userInfo = HttpUtil.authToken("http://localhost:8080/auth?token=" + token);
                    if (userInfo != null) {//token有效
                        request.getSession().setAttribute("user", userInfo);
                        filterChain.doFilter(request, response);
                    } else {//token无效
                        toLogin(response, request, filterChain);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    toLogin(response, request, filterChain);
                }
            } else {//没登录
                toLogin(response, request, filterChain);
            }
        }


    }


    private void toLogin(HttpServletResponse response, HttpServletRequest request, FilterChain filterChain) throws IOException, ServletException {
        StringBuilder sb = new StringBuilder("http://localhost:8080/login?redirect=").append(request.getRequestURL());
        //StringBuilder sb = new StringBuilder("http://www.sso.com/login?redirect=").append(request.getRequestURL());
        response.sendRedirect(sb.toString());

    }

    public void destroy() {

    }

}
