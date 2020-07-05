/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crudsample.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.crudsample.action.LoginAction;
import com.crudsample.action.UsersAction;
import com.crudsample.model.UserInfo;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author simiyu
 */
public class AuthenticationInterceptor implements Interceptor {

    /**
     *
     */
    private static final long serialVersionUID = -3010667957609400791L;
    private Logger log = LoggerFactory.getLogger(getClass());
    private static final String USER_HANDLE = "user";

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();

        HttpSession session = request.getSession(false);

        UserInfo u = session == null ? null : (UserInfo) session.getAttribute(USER_HANDLE);

        if (u == null && !(invocation.getAction().getClass().equals(LoginAction.class) || invocation.getAction().getClass().equals(UsersAction.class))) {
            response.sendRedirect(request.getServletContext().getContextPath() + "/users/gologin");
            return null;
        } else {
            return invocation.invoke();
        }

    }

    public void destroy() {
        // close any resources here
    }

    @Override
    public void init() {
        log.info("Intializing LoginInterceptor");
    }

}
