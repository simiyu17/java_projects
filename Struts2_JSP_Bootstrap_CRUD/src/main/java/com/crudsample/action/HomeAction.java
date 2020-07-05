/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crudsample.action;

import java.util.List;
import com.crudsample.model.Gender;
import com.crudsample.model.Student;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.JSONObject;

/**
 *
 * @author simiyu
 */
@Namespace("/students")
@InterceptorRef(value = "customStack")
@ParentPackage(value = "default")
public class HomeAction extends BaseAction {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private boolean loggedIn;
    private List<Student> students;
    private Student student;

    private static final String REQUEST_ACTION = "ACTION";
    private static final String NEW_STUDENT = "NEW_STUDENT";
    private static final String GET_STUDENT = "GET_STUDENT";
    private static final String EDIT_STUDENT = "EDIT_STUDENT";
    private static final String DELETE_STUDENT = "DELETE_STUDENT";

    @Action(value = "/getStudents", results = {
        @Result(name = SUCCESS, location = "/WEB-INF/jsp/index.jsp")})
    public String doGetStudents() {
        List<Student> students = stdDao.getStudents();
        setStudents(students);
        setLoggedIn(true);
        return SUCCESS;
    }

    @Action(value = "/doAction", results = {
        @Result(name = SUCCESS, type = "json", location = "/WEB-INF/jsp/index.jsp")})
    public String doStudentActions() {
        setLoggedIn(true);
        String action = this.get(REQUEST_ACTION);
        if (action.equals(NEW_STUDENT)) {
            return addNewStudent();
        } else if (action.equals(GET_STUDENT)) {
            return getStudentById();
        } else if (action.equals(EDIT_STUDENT)) {
            return editStudent();
        } else if (action.equals(DELETE_STUDENT)) {
            return deleteStudent();
        } else {
            return ERROR;
        }

    }

    private String addNewStudent() {

        Student st = new Student(this.get("name"), Gender.valueOf(this.get("gender")), this.get("subject"),
                this.get("city"), this.get("country"));
        try {
            stdDao.save(st);
             this.respond(response, true, "<strong>Success</strong><br /> Student succesfully Saved", null);
            return null;
        } catch (Exception ex) {
             this.respond(response, false, "<strong>Failed</strong><br /> An Error Occured", null);
            return null;
        }
    }

    private String getStudentById() {
        Student std = stdDao.findById(new Long(this.get("student_id")));
        setStudent(std);
        JSONObject stdjson = new JSONObject(std);
         this.respond(response, true, "Student Found", stdjson);
        return null;
    }

    private String editStudent() {
        try {
            Student st = stdDao.findById(new Long(this.get("student_id")));
            st.setName(this.get("name"));
            st.setGender(Gender.valueOf(this.get("gender")));
            st.setFavouriteSubject(this.get("subject"));
            st.setCity(this.get("city"));
            st.setCountry(this.get("country"));
            stdDao.save(st);
            this.respond(response, true, "<strong>Success</strong><br /> Student succesfully Updated", null);
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
             this.respond(response, false, "<strong>Failed</strong><br /> An Error Occured", null);
            return null;
        }
    }

    private String deleteStudent() {
        Student s = stdDao.findById(new Long(this.get("student_id")));
        if (s == null) {
             this.respond(response, false, "<strong>Failed</strong><br /> No Student With Id", null);
            return null;
        } else {
            try {
                stdDao.delete(s);
                 this.respond(response, true, "<strong>Success</strong><br /> Student succesfully Removed", null);
                return null;
            } catch (Exception e) {
                 this.respond(response, false, "<strong>Failed</strong><br /> An Error Occured", null);
                return null;
            }
        }
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

    /**
     * @return the students
     */
    public List<Student> getStudents() {
        return students;
    }

    /**
     * @param students the students to set
     */
    public void setStudents(List<Student> students) {
        this.students = students;
    }

    /**
     * @return the student
     */
    public Student getStudent() {
        return student;
    }

    /**
     * @param student the student to set
     */
    public void setStudent(Student student) {
        this.student = student;
    }

}
