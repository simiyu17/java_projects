/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crudsample.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import com.crudsample.model.UserInfo;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONObject;

/**
 *
 * @author simiyu
 */
@Namespace("/users")
@InterceptorRef(value = "customStack")
@ParentPackage(value = "default")
public class LoginAction extends BaseAction implements SessionAware {

    /**
     *
     */
    private static final long serialVersionUID = 153388335972391141L;
    private boolean loggedIn;

    @Override
    public void setSession(Map<String, Object> session) {
    }

    @Action(value = "login", results = {
        @Result(name = SUCCESS, type = "json", location = "/WEB-INF/jsp/index.jsp")})
    public String login() {

        // get request parameters for userID and password
        String username = this.get("username");
        String password = this.get("password");

        UserInfo u = userDao.findByUsername(username);

        if (u != null && u.getPassword().equals(helper.hash(password))) {
            HttpSession sess = request.getSession();
            sess.setAttribute("user", u);
            //setting session to expiry in 30 mins
            sess.setMaxInactiveInterval(30 * 60);
            Cookie userName = new Cookie("username", username);
            userName.setMaxAge(30 * 60);
            response.addCookie(userName);

            this.respond(response, true, "<strong>Welcome " + u.getFullname() + "</strong><br /> You succesfully Logged in", null);

            return null;
        } else {
            this.respond(response, false, "<strong>Failed</strong><br /> Invalid Username and/or password", null);
            return null;
        }

    }

    @Action(value = "/gologin", results = {
        @Result(name = INPUT, location = "/WEB-INF/jsp/login.jsp")
    })
    public String goToLogin() {
        setLoggedIn(false);
        return INPUT;
    }

    @Action(value = "/logout", results = {
        @Result(name = SUCCESS, type = "json", location = "/WEB-INF/jsp/index.jsp")})
    public String doPost() {
        final Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (final Cookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID")) {
                    System.out.println("JSESSIONID=" + cookie.getValue());
                    break;
                }
            }
        }
        // invalidate the session if exists
         final HttpSession ses = request.getSession(false);
        if (ses != null) {
            ses.invalidate();
        }
        this.respond(response, true, "<strong>Good Bye</strong><br /> You succesfully Logged out", null);
        return null;
    }

    /**
     * @return the loggedIn
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * @param loggedIn the loggedIn to set
     */
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

}
