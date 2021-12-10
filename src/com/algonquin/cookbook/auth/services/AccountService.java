package com.algonquin.cookbook.services;

import com.algonquin.cookbook.dao.LinkDao;
import com.algonquin.cookbook.dao.UserDao;
import com.algonquin.cookbook.entity.Link;
import com.algonquin.cookbook.entity.User;
import com.algonquin.cookbook.enums.LinkType;
import com.algonquin.cookbook.util.LinkUtil;
import com.algonquin.cookbook.util.PasswordUtil;
import com.algonquin.cookbook.util.Postman;

import javax.mail.MessagingException;

public class AccountService {

    private UserDao userDao;
    private LinkDao linkDao;
    private PasswordUtil passwordUtil;
    private LinkUtil linkUtil;
    private Postman postman;

    public AccountService() {
        userDao = new UserDao();
        linkDao = new LinkDao();
        passwordUtil = new PasswordUtil();
        linkUtil = new LinkUtil();
        postman = new Postman();
    }

    //how to handle signIn? boolean? flag?
    public Boolean signIn(String email, String password) {
        String hash = passwordUtil.hashPassword(password);
        User user = userDao.findUser(email, hash);
        return user != null && user.getAccountVerified();
    }

    public String signUp(String email, String username, String password) {
        String hash = passwordUtil.hashPassword(password);
        User user = userDao.findUser(email, hash);
        if (user != null) {
            System.out.println("User already exists. Try to Sign In.");
            return "User already exists. Try to Sign In.";
        } else {
            User newUser = new User(email, username, password, false);
            userDao.addUser(newUser);
            String generatedLinkString = linkUtil.generateLink(email);
            Link link = new Link(email, generatedLinkString, LinkType.VERIFICATION, false);
            linkDao.addLink(link);
            try {
                postman.sendEmail(email, generatedLinkString);
            } catch (MessagingException e) {
                System.out.println("Error sending verification link.");
                e.printStackTrace();
            }
            return "Verification link has been sent.";
        }
    }
}
