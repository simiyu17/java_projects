/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sample.model.Gender;
import com.sample.model.Student;

import org.json.JSONObject;

/**
 *
 * @author simiyu
 */
@WebServlet(name = "HomeServlet", urlPatterns = { "/home", "/students" })
public class HomeServlet extends BaseServlet implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static final String REQUEST_ACTION = "ACTION";
    private static final String NEW_STUDENT = "NEW_STUDENT";
    private static final String GET_STUDENT = "GET_STUDENT";
    private static final String EDIT_STUDENT = "EDIT_STUDENT";
    private static final String DELETE_STUDENT = "DELETE_STUDENT";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Student> students = stdDao.getStudents();
        request.setAttribute("students", students);
        request.setAttribute("loggedIn", true);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = this.get(request, REQUEST_ACTION);
        switch (action) {
            case NEW_STUDENT:
                addNewStudent(request, response);
                break;
            case GET_STUDENT:
                getStudent(request, response);
                break;
            case EDIT_STUDENT:
                editStudent(request, response);
                break;
            case DELETE_STUDENT:
                deleteStudent(request, response);
                break;
        }

    }

    private void addNewStudent(HttpServletRequest request, HttpServletResponse response) {

        Student st = new Student(this.get(request, "name"), Gender.valueOf(this.get(request, "gender")), this.get(request, "subject"), this.get(request, "city"), this.get(request, "country"));
        try {
            stdDao.save(st);
            this.respond(response, true, "<strong>Success</strong><br /> Student succesfully Saved", null);
        } catch (Exception ex) {
            this.respond(response, false, "<strong>Failed</strong><br /> An Error Occured", null);
        }
    }

    private void getStudent(HttpServletRequest request, HttpServletResponse response) {
        Student student = stdDao.findById(new Long(this.get(request,"student_id")));
        request.setAttribute("student", student);
        request.setAttribute("loggedIn", true);
        JSONObject std = new JSONObject(student);
        this.respond(response, true, "Student Found", std);
    }

    private void editStudent(HttpServletRequest request, HttpServletResponse response) {
        try {
            Student st = stdDao.findById(new Long(this.get(request,"student_id")));
            st.setName(this.get(request,"name"));
            st.setGender(Gender.valueOf(this.get(request,"gender")));
            st.setFavouriteSubject(this.get(request, "subject"));
            st.setCity(this.get(request,"city"));
            st.setCountry(this.get(request,"country"));
            stdDao.save(st);
            this.respond(response, true, "<strong>Success</strong><br /> Student succesfully Updated", null);
        } catch (Exception ex) {
            ex.printStackTrace();
            this.respond(response, false, "<strong>Failed</strong><br /> An Error Occured", null);
        }
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) {
        Student s = stdDao.findById(new Long(this.get(request,"student_id")));
        if (s == null) {
            this.respond(response, false, "<strong>Failed</strong><br /> No Student With Id", null);
        } else {
            try {
                stdDao.delete(s);
                this.respond(response, true, "<strong>Success</strong><br /> Student succesfully Removed", null);
            } catch (Exception e) {
                this.respond(response, false, "<strong>Failed</strong><br /> An Error Occured", null);
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
