/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samplestrut;

/**
 *
 * @author simiyu
 */

public class HomeAction extends BaseAction  {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    private String students;
  

    public String doGetStudents() {
        setStudents(baseUrl());
        request.setAttribute("loggedIn", true);
        return SUCCESS;
    }

    /**
     * @return the students
     */
    public String getStudents() {
        return students;
    }

    /**
     * @param students the students to set
     */
    public void setStudents(String students) {
        this.students = students;
    }

    

}
