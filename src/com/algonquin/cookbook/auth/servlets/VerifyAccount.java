package com.algonquin.cookbook.servlets;

import com.algonquin.cookbook.dao.LinkDao;
import com.algonquin.cookbook.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/VerifyAccount")
public class VerifyAccount extends HttpServlet {

    private LinkDao linkDao;
    private UserDao userDao;

    public VerifyAccount() {
        linkDao = new LinkDao();
        userDao = new UserDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userEmail = req.getParameter("userEmail");
        String uniqueString = req.getParameter("uniqueString");
        if (linkDao.verifyLink(uniqueString)) {
            linkDao.updateLink(userEmail);
            if (userDao.updateUser(userEmail)) {
                //todo
                resp.sendRedirect("login.jsp");
            } else {
                //todo
                resp.sendRedirect("index.jsp");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
