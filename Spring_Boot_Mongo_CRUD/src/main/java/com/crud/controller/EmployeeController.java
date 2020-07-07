/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crud.controller;

import com.crud.dao.EmployeeDao;
import java.util.List;

import com.crud.model.Employee;
import com.crud.model.Response;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author simiyu
 */
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeDao employeedao;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // -------------------Retrieve All
    // Employees---------------------------------------------
    @GetMapping(value = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Employee> listAllEmployees() throws Exception {
        List<Employee> employees = employeedao.getAllEmployees();
        return employees;
    }

    // -------------------Retrieve Single Employee------------------------------------------
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable("id") String id) {
        Optional<Employee> employee = employeedao.getEmployeeById(id);
        if (!employee.isPresent()) {
            return new ResponseEntity<Response>(new Response(false, "Employee with id " + id + " not found!!"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Optional<Employee>>(employee, HttpStatus.OK);
    }

    // -------------------Create a Employee-------------------------------------------
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) throws Exception {
        try {
            employeedao.createEmployee(employee);
            return new ResponseEntity<Response>(new Response(true, "Successfully Created Employee !!"), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<Response>(new Response(false, "An Error Occured " + e.getMessage()), HttpStatus.NOT_ACCEPTABLE);
        }

    }

    // ------------------- Update a Employee -------------------------------
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable("id") String id, @RequestBody Employee employee) throws Exception {
        try {
            Optional<Employee> currentEmployee = employeedao.getEmployeeById(id);
            if (!currentEmployee.isPresent()) {
                return new ResponseEntity<Response>(new Response(false, "Employee with id " + id + " not found !!"), HttpStatus.NOT_FOUND);
            }
            employee.setEmpId(id);
            employeedao.updateEmployee(employee);
            return new ResponseEntity<Response>(new Response(true, "Successfully Updated Employee."), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Response>(new Response(false, "An Error Occured " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    // ------------------- Delete a Employee-----------------------------------------
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") String id) throws Exception {
        try {
            Optional<Employee> employee = employeedao.getEmployeeById(id);
            if (!employee.isPresent()) {
                return new ResponseEntity<Response>(new Response(false, "Employee with id " + id + " not found !!"), HttpStatus.NOT_FOUND);
            }
            employeedao.deleteEmployee(id);
            return new ResponseEntity<Response>(new Response(true, "Successfully Removed Employee."), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Response>(new Response(false, "An Error Occured " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
