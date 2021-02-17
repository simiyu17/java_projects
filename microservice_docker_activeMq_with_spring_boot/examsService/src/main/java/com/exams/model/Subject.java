package com.exams.model;

public enum Subject {

    MATHS("Mathematics"),
    LANGUAGES("Languages"),
    ARTS("Arts");

    private String name;

    private Subject(String name) {
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
