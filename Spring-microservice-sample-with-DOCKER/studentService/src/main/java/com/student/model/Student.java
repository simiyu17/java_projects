package com.student.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "student")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "dob")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    
    @NotNull
    @Column(name = "fees_status")
    @Enumerated(EnumType.STRING)
    private FeeStatus feeStatus;

    public Student() {
    }

    public Student(String name, Gender gender, String city, String country, Date dateOfBirth) {
        this.name = name;
        this.gender = gender;
        this.city = city;
        this.country = country;
        this.dateOfBirth = dateOfBirth;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return the feeStatus
     */
    public FeeStatus getFeeStatus() {
        return feeStatus;
    }

    /**
     * @param feeStatus the feeStatus to set
     */
    public void setFeeStatus(FeeStatus feeStatus) {
        this.feeStatus = feeStatus;
    }
}
