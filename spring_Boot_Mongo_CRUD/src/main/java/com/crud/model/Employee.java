/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crud.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author simiyu
 */
@Document(collection = "employees")
public class Employee {
    
    @Id
    private String empId;
    
    private String firstname;
	
    private String lastname;
    
    private int age;
    
    private double salary;
        
    @Indexed(unique = true)
    private String email;
    
    public Employee() {
    	
    }

	public Employee(String empId, String firstname, String lastname, int age, double salary, String email) {
		super();
		this.empId = empId;
		this.firstname = firstname;
                this.lastname = lastname;
		this.age = age;
		this.salary = salary;
		this.email = email;
	}


	/**
     * @return the empId
     */
    public String getEmpId() {
        return empId;
    }

    /**
     * @param empId the empId to set
     */
    public void setEmpId(String empId) {
        this.empId = empId;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the salary
     */
    public double getSalary() {
        return salary;
    }

    /**
     * @param salary the salary to set
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }

    /**
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public String toString(){
        return "Employee [empId=" + empId + ", firstname=" + firstname + ", lastname=" + lastname + ", age=" + age + ", salary=" + salary + ", email=" + email + "]";
    }
    
}
