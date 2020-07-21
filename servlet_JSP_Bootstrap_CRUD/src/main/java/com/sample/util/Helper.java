/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.util;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 *
 * @author simiyu
 */

public class Helper {
    
    private static final String MESSAGE = "message";
    private static final String SUCCESS = "success";
 
    public static final long serialVersionUID = 1L;

 
     public String toString(Object o)
    {
        try {
            return o.toString();
        } catch (NullPointerException npe) {
            return "";
        }
    }
    public String encrypt(String string)
    {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(string.getBytes());
            byte byteData[] = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException nsae) {
            return string;
        }

    }

    public String hash(String password)
    {
        String sha1 = "";
        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(password.getBytes("UTF-8"));
            sha1 = byteToHex(crypt.digest());
        }
        catch(NoSuchAlgorithmException | UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return sha1;
    }


    private String byteToHex(final byte[] hash)
    {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    public String result(boolean status, String message, JSONObject json)
    {
        JSONObject obj = new JSONObject();
        try {
            obj.put(Helper.SUCCESS, status);
            obj.put(Helper.MESSAGE, message);
            obj.put("data", json != null ? json.toString() : json);
            return obj.toString();
        } catch (JSONException je) {
            return null;
        }
    }


    public JSONObject response(boolean status, String message)
    {
        try {
            return new JSONObject().put("success", status).put("message", message);
        } catch (JSONException je) {
            return null;
        }
    }
   
}

