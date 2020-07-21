/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crud.controller;

import com.crud.dao.StudentDaoImpl;
import com.crud.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.mockito.ArgumentMatchers;

/**
 *
 * @author simiyu
 */
@WebMvcTest
public class UnitestingStudentControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentDaoImpl studentdao;

    private List<Student> students;

    private static ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        Calendar cal = Calendar.getInstance();
        cal.set(2000, 06, 23);
        this.students = new ArrayList<>();
        students.add(new Student("Student One", "Kitale", cal.getTime()));
        students.add(new Student("Student Two", "Eldoret", cal.getTime()));
        students.add(new Student("Student Three", "Nakuru", cal.getTime()));
        students.add(new Student("Student Four", "Machakosi", cal.getTime()));

    }

    @Test
    public void shouldReturnAllAvailableStudents() throws Exception {

        when(studentdao.getStudents()).thenReturn(this.students);

        this.mockMvc.perform(get("/students/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(4)))
                .andExpect(jsonPath("$[0].fullName", Matchers.equalTo("Student One")));

    }

    @Test
    public void shouldReturnStudentById() throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.set(2000, 06, 23);
        final Long stdId = 1L;
        Student student = new Student("Student One", "Kitale", cal.getTime());
        student.setId(stdId);
        when(studentdao.findById(stdId)).thenReturn(student);

        this.mockMvc.perform(get("/students/{id}", stdId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.aMapWithSize(4)))
                .andExpect(jsonPath("$.fullName", Matchers.equalTo("Student One")));

    }

    @Test
    public void shouldReturn_404_IfStudent_NOT_FOUND_ById() throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.set(2000, 06, 23);
        final Long stdId = 1L;
        Student student = new Student("Student One", "Kitale", cal.getTime());
        student.setId(stdId);
        final Long stdId_not_present = 3L;

        when(studentdao.findById(stdId)).thenReturn(student);

        this.mockMvc.perform(get("/students/{id}", stdId_not_present)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.msg", Matchers.equalTo("Student with id " + stdId_not_present + " not found!!")));

    }

    @Test
    public void shouldCreateNewStudentSuccessfully() throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.set(2000, 06, 23);
        final Long stdId = 1L;
        Student student = new Student("Student One", "Kitale", cal.getTime());
        student.setId(stdId);
        String json = mapper.writeValueAsString(student);

        when(studentdao.save(ArgumentMatchers.any())).thenReturn(student);

        this.mockMvc.perform(post("/students/").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
                .andExpect(jsonPath("$.msg", Matchers.equalTo("Successfully Created Student !!")))
                .andExpect(jsonPath("$.success", Matchers.equalTo(true)));

    }

    @Test
    public void shouldUpdateStudentDetails() throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.set(2000, 06, 23);
        final Long stdId = 2L;
        Student student = new Student("Student One", "Kitale", cal.getTime());
        student.setId(stdId);
        String json = mapper.writeValueAsString(student);

        when(studentdao.findById(ArgumentMatchers.any())).thenReturn(student);
        when(studentdao.save(ArgumentMatchers.any())).thenReturn(student);

        mockMvc.perform(put("/students/{id}", stdId).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.msg", Matchers.equalTo("Successfully Updated Student.")))
                .andExpect(jsonPath("$.success", Matchers.equalTo(true)));
    }

    @Test
    public void shouldDeleteStudent() throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.set(2000, 06, 23);
        final Long stdId = 2L;
        Student student = new Student("Student One", "Kitale", cal.getTime());
        student.setId(stdId);

        when(studentdao.findById(ArgumentMatchers.any())).thenReturn(student);
        doNothing().when(studentdao).delete(student);

        this.mockMvc.perform(delete("/students/{id}", stdId)).andExpect(status().isOk());

    }
}
