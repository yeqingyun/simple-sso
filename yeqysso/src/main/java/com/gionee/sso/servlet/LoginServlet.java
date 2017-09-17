package com.gionee.sso.servlet;

import com.gionee.sso.util.UserSessionMap;
import com.yeqy.sso.biz.User;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yeqy on 2017/9/13.
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String account = req.getParameter("account");
        String password = req.getParameter("password");
        String redirect = req.getParameter("redirect");
        User user = null;

        if ("admin".equals(account) && "123456".equals(password)) {//登录成功
            user = new User(account, password);
            req.getSession().setAttribute("user", user);
            UserSessionMap.setUser(account, user);

            Cookie cookie = new Cookie("token", account);
            cookie.setPath("/");
            resp.addCookie(cookie);
        } else {//账号密码错误
            req.setAttribute("message", "Account/Password Error");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }

        if (redirect != null && redirect.trim().length() > 0) {
            //req.setAttribute("token", account);
            //req.getRequestDispatcher(redirect).forward(req, resp);
            if (redirect.endsWith("/")) {
                resp.sendRedirect(redirect.substring(0, redirect.length() - 1) + "?token=" + account);
            }
        } else {
            resp.sendRedirect("/index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
