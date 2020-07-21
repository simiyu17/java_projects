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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author simiyu
 */
@WebServlet(name = "UserServlet", urlPatterns = {"/users"})
public class UserServlet extends BaseServlet implements Serializable {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        String fullname = this.get(request,"fullname");
        String password = this.get(request,"password");
        String username = this.get(request,"username");
        UserInfo u = new UserInfo(username, password, fullname);

        UserInfo u2 = userDao.findByUsername(u.getUsername());
        if (u2 != null) {
            this.respond(response, false, "<strong>Failed</strong><br /> Username already exists", null);
        } else {
            try {
                userDao.save(u);
                this.respond(response, true, "<strong>Welcome " + u.getFullname() + "</strong><br /> You will redirected>>>", null);
            } catch (Exception ex) {
                this.respond(response, false, "<strong>Failed</strong><br /> An Error Occured", null);
            }
        }

    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("loggedIn", false);
        request.getRequestDispatcher("users.jsp").forward(request, response);
    }

}
