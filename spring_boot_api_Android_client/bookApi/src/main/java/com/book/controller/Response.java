/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.book.controller;

import java.io.Serializable;

/**
 *
 * @author simiyu
 */
public class Response implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private boolean success;

    private String msg;

    private String token;

    private String userFullName;

    public Response() {
    }

    public Response(boolean success, String msg, String token, String userFullName) {
        this.success = success;
        this.msg = msg;
        this.token = token;
        this.userFullName = userFullName;
    }

    /**
     * @return the success
     */
    public boolean getSuccess() {
        return success;
    }

    /**
     * @param success the success to set
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

}
