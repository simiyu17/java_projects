/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.controller;

import com.sample.dao.student.StudentDaoImpl;
import com.sample.dao.user.UserDaoImpl;
import com.sample.util.Helper;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author simiyu
 */
public class BaseServlet extends HttpServlet {

    protected Logger log = LoggerFactory.getLogger(getClass());
    protected final Helper helper = new Helper();

    protected StudentDaoImpl stdDao = new StudentDaoImpl();

    protected UserDaoImpl userDao = new UserDaoImpl();

    protected String get(HttpServletRequest req, String param) {
        return helper.toString(req.getParameter(param));
    }

    protected String baseUrl() {
        return getServletContext().getContextPath() + "/";
    }

    protected String getBasePath() {
        return getServletContext().getRealPath("/") + "/";
    }

    void redirect(HttpServletResponse resp, String page) {
        try {
            resp.sendRedirect(getServletContext().getContextPath() + page);
        } catch (IOException ioe) {
            ioe.printStackTrace();
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
