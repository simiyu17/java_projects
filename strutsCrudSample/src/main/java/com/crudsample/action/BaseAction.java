/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crudsample.action;

import com.crudsample.dao.student.StudentDaoImpl;
import com.crudsample.dao.user.UserDaoImpl;
import com.crudsample.util.Helper;
import com.opensymphony.xwork2.ActionSupport;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author simiyu
 */
public class BaseAction extends ActionSupport {

    /**
     *
     */
    private static final long serialVersionUID = -7885144665626813758L;
    protected Logger log = LoggerFactory.getLogger(getClass());
    protected final Helper helper = new Helper();

    protected StudentDaoImpl stdDao = new StudentDaoImpl();

    protected UserDaoImpl userDao = new UserDaoImpl();

    protected HttpServletRequest request = ServletActionContext.getRequest();
    protected HttpServletResponse response = ServletActionContext.getResponse();

    protected String get(String param) {
        return helper.toString(request.getParameter(param));
    }

    protected String baseUrl() {
        return request.getServletContext().getContextPath() + "/";
    }

    protected String getBasePath() {
        return request.getServletContext().getRealPath("/") + "/";
    }

    void redirect(HttpServletResponse resp, String page) {
        try {
            resp.sendRedirect(ServletActionContext.getServletContext().getContextPath() + page);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    protected JSONObject respond(boolean status, String message, JSONObject data) {
        try {
            JSONObject response = helper.response(status, message, data);
            return response;
        } catch (Exception ioe) {
            log.error("Problem sending response" + ioe.getMessage());
            return null;
        }

    }
    
     protected void respond(HttpServletResponse resp, boolean status, String message, JSONObject json) {
        if (json != null) {
            log.info("Object is : " + json.toString());
        }
        try {
            String response = helper.result(status, message, json);
            log.info(response);
            resp.getWriter().write(response);
        } catch (IOException ioe) {
            log.error("Problem sending response" + ioe.getMessage());
        }

    }

}
