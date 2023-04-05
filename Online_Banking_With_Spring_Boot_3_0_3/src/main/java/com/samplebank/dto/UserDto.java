/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.samplebank.dto;

import java.io.Serializable;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author simiyu
 */
@Setter
public class UserDto implements Serializable{

    private static String PASSWORD_HIDDEN = "<<Hidden>>";
    
    private Long id;

    private String username;

    private String password;

    private Set<String> roles;

    public UserDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return PASSWORD_HIDDEN;
    }

    public Set<String> getRoles() {
        return roles;
    }
}
