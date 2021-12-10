package com.algonquin.cookbook.servlets;

import com.algonquin.cookbook.dao.LinkDao;
import com.algonquin.cookbook.dao.UserDao;
import com.algonquin.cookbook.entity.Link;
import com.algonquin.cookbook.entity.User;
import com.algonquin.cookbook.enums.LinkType;
import com.algonquin.cookbook.util.LinkUtil;
import com.algonquin.cookbook.util.PasswordUtil;
import com.algonquin.cookbook.util.Postman;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/RegisterUser")
public class RegisterUser extends HttpServlet {

    private UserDao userDao;
    private LinkDao linkDao;
    private PasswordUtil passwordUtil;
    private LinkUtil linkUtil;
    private Postman postman;

    public RegisterUser() {
        userDao = new UserDao();
        linkDao = new LinkDao();
        passwordUtil = new PasswordUtil();
        linkUtil = new LinkUtil();
        postman = new Postman();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        String hash = passwordUtil.hashPassword(password);
        User user = userDao.findUser(email, hash);
        if (user != null) {
            System.out.println("User already exists. Try to Sign In.");
        } else {
            User newUser = new User(email, username, password, false);
            userDao.addUser(newUser);
            String generatedLinkString = linkUtil.generateLink(email);
            Link link = new Link(email, generatedLinkString, LinkType.VERIFICATION, false);
            linkDao.addLink(link);
            try {
                postman.sendEmail(email, generatedLinkString);
                //todo
                resp.sendRedirect("verify.jsp");
            } catch (MessagingException e) {
                System.out.println("Error sending verification link.");
                e.printStackTrace();
            }
        }
    }
}
