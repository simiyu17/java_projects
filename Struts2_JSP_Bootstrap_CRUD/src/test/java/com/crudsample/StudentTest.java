/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crudsample;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;
import java.util.List;

import com.crudsample.dao.student.StudentDaoImpl;
import com.crudsample.model.Gender;
import com.crudsample.model.Student;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author simiyu
 */
public class StudentTest {

    private StudentDaoImpl stdDao = new StudentDaoImpl();
    private Logger log = LoggerFactory.getLogger(getClass());

    @BeforeAll
    public static void addData() {
        Logger log = LoggerFactory.getLogger(UsersTest.class);
        try {
            StudentDaoImpl stdDao2 = new StudentDaoImpl();
            Student st = new Student("TESTNAME", Gender.FEMALE, "NONE", "New York", "USA");
            stdDao2.save(st);
        } catch (SQLException e) {
            log.error("Error Connection To Datasource. Please Check Datasource Connection");

        } catch (NullPointerException e) {
            log.error("Error Connection To Datasource. Please Check Datasource Connection");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetStudent() {
        List<Student> stds = stdDao.getStudents();
        assertTrue(stds.size() > 0, "Students were not added");

        Boolean stdfound = false;
        for(Student st: stds){
            if(st.getName().equals("TESTNAME")){
                stdfound = true;
            }
        }
        assertTrue(stdfound, "Students Search not working as expected");
    }

    @Test
    public void testThrows() {
        Student st = null;
        Exception exception = assertThrows(NullPointerException.class, () -> st.getName());
        log.info(exception.getMessage());
    }

    @AfterAll
    public static void removeData() throws Exception {
        StudentDaoImpl stdDao2 = new StudentDaoImpl();
        List<Student> stds = stdDao2.getStudents();
        for(Student st: stds){
            if(st.getName().equals("TESTNAME")){
                stdDao2.delete(st);
            }
        }
    }

}
