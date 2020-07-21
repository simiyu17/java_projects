/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crud.repository;

import com.crud.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author simiyu
 */
@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {

    @Query("{ 'firstname' : ?0 }")
    public Employee findByFirstname(String firstname);

    @Query("{ 'email' : ?0 }")
    public Employee findByEmail(String email);

}
