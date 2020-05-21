/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.controller;

import com.sample.model.UserInfo;
import java.io.IOException;
import java.io.Serializable;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author simiyu
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends BaseServlet implements Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        // get request parameters for userID and password
        String username = this.get(request,"username");
        String password = this.get(request,"password");
        UserInfo u = userDao.findByUsername(username);

        if (u != null && u.getPassword().equals(helper.hash(password))) {
            HttpSession session = request.getSession();
            session.setAttribute("user", u);
            //setting session to expiry in 30 mins
            session.setMaxInactiveInterval(30 * 60);
            Cookie userName = new Cookie("username", username);
            userName.setMaxAge(30 * 60);
            response.addCookie(userName);
            this.respond(response, true, "<strong>Welcome " + u.getFullname() + "</strong><br /> You succesfully Logged in", null);
        } else {
            this.respond(response, false, "<strong>Failed</strong><br /> Invalid Username and/or password", null);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("loggedIn", false);
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
