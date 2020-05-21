package com.sample.model;

public enum Gender {
    
    MALE("Male"),
    FEMALE("Female");

    private String name;

    private Gender(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    public String toString() {
        return name;
    }

 
}