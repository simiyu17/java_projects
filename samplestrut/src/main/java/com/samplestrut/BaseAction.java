/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samplestrut;

import com.opensymphony.xwork2.ActionSupport;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author simiyu
 */
public class BaseAction extends ActionSupport implements ServletRequestAware{

    /**
     *
     */
    private static final long serialVersionUID = -7885144665626813758L;

    protected HttpServletRequest request;

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
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

 
}
