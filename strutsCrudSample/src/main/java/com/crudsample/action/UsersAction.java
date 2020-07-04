/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crudsample.action;

import com.crudsample.model.UserInfo;
import org.apache.struts2.convention.annotation.*;

/**
 *
 * @author simiyu
 */
@Namespace("/users")
@InterceptorRef(value = "customStack")
@ParentPackage(value = "default")
public class UsersAction extends BaseAction {

    /**
     *
     */
    private static final long serialVersionUID = -3915767746722375225L;

    private boolean loggedIn;

    @Action(value = "/doSignup", results = {
        @Result(name = SUCCESS, type = "json", location = "/WEB-INF/jsp/login.jsp")})
    public String doPost() {

        String fullname = this.get("fullname");
        String password = this.get("password");
        String username = this.get("username");
        UserInfo u = new UserInfo(username, password, fullname);

        UserInfo u2 = userDao.findByUsername(u.getUsername());
        if (u2 != null) {
            this.respond(response, false, "<strong>Failed</strong><br /> Username already exists", null);
            return null;
        } else {
            try {
                userDao.save(u);
                this.respond(response, true, "<strong>Welcome " + u.getFullname() + "</strong><br /> You will redirected>>>", null);
                return null;
            } catch (Exception ex) {
                this.respond(response, false, "<strong>Failed</strong><br /> An Error Occured", null);
                return null;
            }
        }

    }

    @Action(value = "/goSignup", results = {
        @Result(name = INPUT, location = "/WEB-INF/jsp/users.jsp")})
    public String goSignup() {
        setLoggedIn(false);
        return INPUT;
    }

    /**
     * @return the loggedIn
     */
    public boolean getLoggedIn() {
        return loggedIn;
    }

    /**
     * @param loggedIn the loggedIn to set
     */
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

}
