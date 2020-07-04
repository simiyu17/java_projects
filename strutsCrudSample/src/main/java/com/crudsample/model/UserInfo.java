package com.crudsample.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_info")
public class UserInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Column(name = "name")
    private String fullname;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    public UserInfo(){}

    public UserInfo(String username, String password, String fullname) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
    }


    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}