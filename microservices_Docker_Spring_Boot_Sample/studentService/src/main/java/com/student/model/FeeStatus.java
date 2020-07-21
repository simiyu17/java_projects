package com.student.model;

public enum FeeStatus {
    
    HAVE_ARREARS("Not Fully Paid"),
    FULLY_PAID("Fully Paid");

    private String name;

    private FeeStatus(String name) {
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