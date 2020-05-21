package com.sample.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "fav_subject")
    private String favouriteSubject;

    public Student (){}

    public Student (String name, Gender gender, String favouriteSubject, String city, String country){
        this.name = name;
        this.gender = gender;
        this.favouriteSubject = favouriteSubject;
        this.city = city;
        this.country = country;
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

    public String getFavouriteSubject() {
        return favouriteSubject;
    }

    public void setFavouriteSubject(String favouriteSubject) {
        this.favouriteSubject = favouriteSubject;
    }

}