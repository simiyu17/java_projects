/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crud.dao;

import com.crud.model.Employee;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author simiyu
 */
public interface EmployeeDao {

    public void createEmployee(Employee employee);

    public Employee getEmployeeByFirstName(String firstname);

    public Employee getEmployeeByEmail(String email);
    
    public Optional<Employee> getEmployeeById(String id);

    public List<Employee> getAllEmployees();

    public void updateEmployee(Employee employee);

    public void deleteEmployee(String id);

}
