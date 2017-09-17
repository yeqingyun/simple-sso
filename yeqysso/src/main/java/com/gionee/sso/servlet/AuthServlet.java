package com.gionee.sso.servlet;

import com.gionee.sso.util.UserSessionMap;
import com.yeqy.sso.biz.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * Created by yeqy on 2017/9/12.
 */
public class AuthServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println(req.getSession().getId());
//        Cookie[] cs = req.getCookies();
//        for (Cookie c : cs) {
//            System.out.println(c.getDomain() + "\t" + c.getMaxAge() + "\t" + c.getName() + "=" + c.getValue());
//        }
        User user = UserSessionMap.getUser(req.getParameter("token"));
        if (user != null) {
            OutputStream os = resp.getOutputStream();
            ObjectOutputStream objOutput = new ObjectOutputStream(os);
            objOutput.writeObject(user);
            objOutput.flush();
            objOutput.close();
            os.close();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
